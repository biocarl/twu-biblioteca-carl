package com.twu.biblioteca.ui;

import com.twu.biblioteca.ItemController;
import com.twu.biblioteca.domain.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class MainMenu {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;
    private ItemController itemController;

    public MainMenu(PrintStream printStream, BufferedReader bufferedReader, ItemController itemController) {
        this.printStream = printStream;
        this.itemController = itemController;
        this.bufferedReader = bufferedReader;
    }

    public void printAvailableItems() {
        for (Item item : itemController.getAvailableItemsOfType()) {
            this.printStream.println(item.toString());
        }
    }

    public void printMenu() {
        printStream.println("1: List of available books");
        printStream.println("2: Check-Out a book");
        printStream.println("3: Return a book");
        printStream.println("0: Exit program");
    }

    public void selectItem() throws IOException {
        switch (bufferedReader.readLine()) {
            case "1":
                printAvailableItems();
                break;
            case "2":
                itemController.setItemType("book");
                CheckoutMenu checkoutMenu = new CheckoutMenu(printStream, bufferedReader, itemController);
                checkoutMenu.inflate();
                break;
            case "3":
                itemController.setItemType("book");
                ReturnMenu returnMenu = new ReturnMenu(printStream,bufferedReader, itemController);
                returnMenu.inflate();
                break;
            case "0":
                exit();
                break;
            default:
                this.printStream.println("Please select a valid option!");
        }
    }

    private void exit() {
        System.exit(0);
    }

    public void inflate() throws IOException {
        printMenu();
        selectItem();
    }
}
