package com.twu.biblioteca.functional;

import com.twu.biblioteca.ItemController;
import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.ui.ReturnMenu;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static com.twu.biblioteca.TestHelper.getInMemoryDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ReturnMenuFunctionalTest {
    ItemController itemController;

    @Before
    public void setUp() {
        itemController = new ItemController(getInMemoryDatabase());
    }

    @Test
    public void returnItem_whenBeforeCheckedOutItem_isAvailableAgainInItems() throws IOException {
        final int itemId = 1;
        itemController.checkoutItem(itemId);
        final String checkedOutItemTitle = itemController.getById(itemId).getTitle();
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn(checkedOutItemTitle); //Input name of checked-out Item
        assertThat(false, is(itemController.getAvailableItemsOfType().stream().anyMatch(e -> e.getTitle().equals(checkedOutItemTitle))));

        ReturnMenu returnMenu = new ReturnMenu(spyPrintStream, bufferedReader, itemController);
        returnMenu.inflate();

        assertThat(true, is(itemController.getAvailableItemsOfType().stream().anyMatch(e -> e.getTitle().equals(checkedOutItemTitle))));
    }


    @Test
    public void returnItem_whenValidItemTitleAndCheckedOutItem_isReturningSuccessMessage() throws IOException {
        final int itemId = 1;
        itemController.checkoutItem(itemId);
        itemController.setItemType("book");
        final String checkedOutItemTitle = itemController.getById(itemId).getTitle();
        final String checkedOutItemType = itemController.getById(itemId).getType();

        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn(checkedOutItemTitle); //Input name of checked-out item

        ReturnMenu returnMenu = new ReturnMenu(spyPrintStream, bufferedReader, itemController);
        returnMenu.inflate();

        assertThat(spyPrintStream.printedStrings().get(0), is("Thank you for returning the " + checkedOutItemType));
    }

    @Test
    public void returnItem_whenInValidItemTitle_isReturningFailureMessage() throws IOException {
        final int itemId = 1;
        itemController.checkoutItem(itemId);
        final String checkedOutItemTitle = itemController.getById(itemId).getTitle();

        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn(checkedOutItemTitle + "ABC"); //Input name of checked-out book

        ReturnMenu returnMenu = new ReturnMenu(spyPrintStream, bufferedReader, itemController);
        returnMenu.inflate();

        assertThat(spyPrintStream.printedStrings().get(0), is(String.format("This is not a valid %s to return.", itemController.getItemType())));
    }

}
