package com.twu.biblioteca.ui;

import com.twu.biblioteca.controller.ItemController;
import com.twu.biblioteca.domain.Item;

import java.io.PrintStream;

public class ListMenu implements Menu {

    private final PrintStream printStream;
    private final ItemController itemController;

    public ListMenu(PrintStream printStream, ItemController itemController) {
        this.printStream = printStream;
        this.itemController = itemController;
    }

    public void inflate() {
        for (Item item : itemController.getAvailableItemsOfType()) {
            this.printStream.println(item.toString());
        }
    }
}
