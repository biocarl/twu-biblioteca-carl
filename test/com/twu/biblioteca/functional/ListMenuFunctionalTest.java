package com.twu.biblioteca.functional;


import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.controller.ItemController;
import com.twu.biblioteca.ui.ListMenu;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.TestHelper.getInMemoryItemDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListMenuFunctionalTest {
    ItemController itemController;

    @Before
    public void setUp() {
        itemController = new ItemController(getInMemoryItemDatabase());
    }

    @Test
    public void printAllItems_printsExistingItemsWithDetailsAndFormatted() {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        ListMenu listMenu = new ListMenu(spyPrintStream, itemController);
        listMenu.inflate();

        assertThat(spyPrintStream.printedStrings().get(0), is("" + itemController.getById(1)));
        assertThat(spyPrintStream.printedStrings().get(1), is("" + itemController.getById(2)));
    }

}
