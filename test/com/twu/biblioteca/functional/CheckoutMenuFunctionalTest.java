package com.twu.biblioteca.functional;

import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.ItemController;
import com.twu.biblioteca.ui.CheckoutMenu;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static com.twu.biblioteca.TestHelper.getItems;
import static com.twu.biblioteca.TestHelper.getInMemoryDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CheckoutMenuFunctionalTest {
    ItemController itemController;

    @Before
    public void setUp() {
        itemController = new ItemController(getInMemoryDatabase());
    }

    @Test
    public void printItemOptions_printsAvailableItemsWithUniqueNumber() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        CheckoutMenu checkoutMenu = new CheckoutMenu(spyPrintStream, bufferedReader, itemController);
        checkoutMenu.printAllItems();

        assertThat(spyPrintStream.printedStrings().get(0), is("1\t" + itemController.getById(1)));
        assertThat(spyPrintStream.printedStrings().get(1), is("2\t" + itemController.getById(2)));
    }

    @Test
    public void selectOption_whenSelectingItemForCheckout_itemIsCheckout() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select first item

        CheckoutMenu checkoutMenu = new CheckoutMenu(spyPrintStream, bufferedReader, itemController);
        assertThat(false, is(itemController.getById(1).isCheckout()));

        checkoutMenu.selectItem();

        assertThat(true, is(itemController.getById(1).isCheckout()));
    }

    @Test
    public void selectOption_whenSelectingItemAvailableItemForCheckout_positiveResponseMessageIsPrinted() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select first item

        CheckoutMenu checkoutMenu = new CheckoutMenu(spyPrintStream, bufferedReader, itemController);
        checkoutMenu.selectItem();

        assertThat(spyPrintStream.printedStrings().get(0), is("Thank you! Enjoy the " + itemController.getItemType()));
    }

    @Test
    public void selectOption_whenSelectingItemNotAvailableForCheckout_negativeResponseMessageIsPrinted() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select first item

        ItemController controller = mock(ItemController.class);
        final String type = "type";

        when(controller.getAllItemsOfType()).thenReturn(getItems());
        when(controller.checkoutItem(1)).thenReturn(false);
        when(controller.getItemType()).thenReturn(type);

        CheckoutMenu checkoutMenu = new CheckoutMenu(spyPrintStream, bufferedReader, controller);
        checkoutMenu.selectItem();

        assertThat(spyPrintStream.printedStrings().get(0), is(String.format("Sorry, that %s is not available", type)));
    }
}
