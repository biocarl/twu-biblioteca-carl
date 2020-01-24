package com.twu.biblioteca.ui;

import com.twu.biblioteca.ItemController;
import com.twu.biblioteca.domain.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class CheckoutMenu implements Menu {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;
    private final ItemController itemController;

    public CheckoutMenu(PrintStream printStream, BufferedReader bufferedReader, ItemController itemController) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.itemController = itemController;
    }

    public void inflate() throws IOException {
        printAllItems();
        selectItem();
    }

    public void printAllItems() {
        int i = 1;
        for (Item item : itemController.getAllItemsOfType()) {
            this.printStream.println(i + "\t" + item.toString());
            i++;
        }
    }

    public void selectItem() throws IOException {
        int selection = Integer.parseInt(bufferedReader.readLine()) - 1; // enumeration starts with 1, array with 0
        List<Item> allItems = itemController.getAllItemsOfType();
        if (selection >= 0 && selection < allItems.size()) {
            final boolean checkoutSuccessful = itemController.checkoutItem(allItems.get(selection).getID());
            if (checkoutSuccessful) {
                this.printStream.println("Thank you! Enjoy the " + itemController.getItemType());
            } else {
                this.printStream.println(String.format("Sorry, that %s is not available", itemController.getItemType()));
            }
        }
    }
}
