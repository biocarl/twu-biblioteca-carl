package com.twu.biblioteca.controller;

import com.twu.biblioteca.domain.Item;
import com.twu.biblioteca.repository.ItemRepository;
import com.twu.biblioteca.security.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.ArrayList;

import static com.twu.biblioteca.TestHelper.getInMemoryUserDatabase;
import static com.twu.biblioteca.TestHelper.getItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class ItemControllerTest {

    @Before
    public void setUp() {
        Session.setup(getInMemoryUserDatabase());
        Session.initSession("Carl", "password1");
    }

    @Test
    public void availableItems_returnsOnlyNotCheckedOutItems() throws IOException {
        ItemRepository spyItemRepository = mock(ItemRepository.class);
        ArrayList<Item> items = getItems();
        items.get(0).setIsCheckout(true);
        when(spyItemRepository.getAll()).thenReturn(items);
        ItemController controller = new ItemController(spyItemRepository);

        assertThat(controller.getAvailableItemsOfType().size(), is(getItems().size() - 1));
    }

    @Test
    public void availableItems_whenSettingMovieType_returnsOnlyMoviesItems() throws IOException {
        ItemRepository spyItemRepository = mock(ItemRepository.class);
        ArrayList<Item> items = getItems();
        items.get(0).setIsCheckout(true);
        when(spyItemRepository.getAll()).thenReturn(items);
        ItemController controller = new ItemController(spyItemRepository);
        controller.setItemType("movie");
        controller.getAvailableItemsOfType();

        int firstMovieIDInDummyData = getItems().stream().filter(e -> e.getType().equals("movie")).findFirst().get().getId();
        assertThat(controller.getAvailableItemsOfType().get(0).getId() == firstMovieIDInDummyData, is(true));
    }

    @Test
    public void checkoutItem_whenNotCheckedOutBeforeAndMovieType_returnsSuccessful() throws IOException {
        ItemRepository spyItemRepository = mock(ItemRepository.class);
        ArrayList<Item> items = getItems();
        int firstMovieIDInDummyData = items.stream().filter(e -> e.getType().equals("movie")).findFirst().get().getId();
        Item item = getItems().get(firstMovieIDInDummyData);
        assertThat(item.isCheckout(), is(false));
        when(spyItemRepository.getById(firstMovieIDInDummyData)).thenReturn(item);

        ItemController controller = new ItemController(spyItemRepository);
        controller.setItemType("movie");
        boolean checkOutSuccessful = controller.checkoutItem(firstMovieIDInDummyData);

        assertThat(checkOutSuccessful, is(true));
    }

    @Test
    public void checkoutItem_whenNotCheckedOutBefore_returnsSuccessful() throws IOException {
        ItemRepository spyItemRepository = mock(ItemRepository.class);
        ArrayList<Item> items = getItems();
        Item item = getItems().get(0);
        assertThat(item.isCheckout(), is(false));
        when(spyItemRepository.getById(1)).thenReturn(item);

        ItemController controller = new ItemController(spyItemRepository);
        boolean checkOutSuccessful = controller.checkoutItem(1);

        assertThat(checkOutSuccessful, is(true));
    }

    @Test
    public void checkoutItem_whenAlreadyCheckedOut_returnsUnSuccessful() throws IOException {
        ItemRepository spyItemRepository = mock(ItemRepository.class);
        ArrayList<Item> items = getItems();
        Item item = getItems().get(0);
        item.setIsCheckout(true);
        assertThat(item.isCheckout(), is(true));
        when(spyItemRepository.getById(1)).thenReturn(item);

        ItemController controller = new ItemController(spyItemRepository);
        boolean checkOutSuccessful = controller.checkoutItem(1);

        assertThat(checkOutSuccessful, is(false));
    }

    @Test
    public void returnItem_whenItemNameInvalid_returnsUnSuccessful() throws IOException {
        ItemRepository spyItemRepository = mock(ItemRepository.class);
        ArrayList<Item> items = getItems();
        String itemName = items.get(0).getTitle();
        when(spyItemRepository.getAll()).thenReturn(items);

        ItemController controller = new ItemController(spyItemRepository);
        boolean returnIsSuccessful = controller.returnItem(itemName + "ABC");

        assertThat(returnIsSuccessful, is(false));

    }


    @Test
    public void returnItem_whenCalledOnValidItemNameAndCheckedOutItem_isSuccessfulAndItemUserAssociationIsReset() {
        ItemRepository spyItemRepository = mock(ItemRepository.class);
        ArrayList<Item> items = getItems();
        items.get(0).setIsCheckout(true);
        items.get(0).setUserId(Session.getUser().getId());
        String itemName = items.get(0).getTitle();
        when(spyItemRepository.getAll()).thenReturn(items);
        ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);

        ItemController controller = new ItemController(spyItemRepository);
        boolean returnIsSuccessful = controller.returnItem(itemName);

        verify(spyItemRepository).update(argument.capture());
        assertEquals(0, argument.getValue().getUserId());
        assertThat(returnIsSuccessful, is(true));
    }
}
