package com.twu.biblioteca.ui;

import com.twu.biblioteca.controller.ItemController;
import com.twu.biblioteca.controller.UserController;
import com.twu.biblioteca.security.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

public class MainMenu implements Menu {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;
    private ItemController itemController;
    private UserController userController;

    public MainMenu(PrintStream printStream, BufferedReader bufferedReader, ItemController itemController, UserController userController) {
        this.printStream = printStream;
        this.itemController = itemController;
        this.userController = userController;
        this.bufferedReader = bufferedReader;
    }

    public void printMenu() {
        printStream.println("1: List all available books");
        printStream.println("2: Checkout a book");
        printStream.println("3: Return a book");
        printStream.println("4: List of available movies");
        printStream.println("5: Checkout a movie");
        printStream.println("6: Return a movie");
        if (Session.getUser().isAdmin()) {
            printStream.println("7: [Admin] View books checked out");
        } else {
            printStream.println("7: View profile");
        }
        printStream.println("0: Exit program");
    }

    public void selectItem() throws IOException {
        Optional<Menu> menu = Optional.empty();

        switch (bufferedReader.readLine()) {
            case "1":
                itemController.setItemType("book");
                menu = Optional.of(new ListMenu(printStream, itemController));
                break;
            case "2":
                itemController.setItemType("book");
                menu = Optional.of(new CheckoutMenu(printStream, bufferedReader, itemController));
                break;
            case "3":
                itemController.setItemType("book");
                menu = Optional.of(new ReturnMenu(printStream, bufferedReader, itemController));
                break;
            case "4":
                itemController.setItemType("movie");
                menu = Optional.of(new ListMenu(printStream, itemController));
                break;
            case "5":
                itemController.setItemType("movie");
                menu = Optional.of(new CheckoutMenu(printStream, bufferedReader, itemController));
                break;
            case "6":
                itemController.setItemType("movie");
                menu = Optional.of(new ReturnMenu(printStream, bufferedReader, itemController));
                break;
            case "7":
                if (Session.getUser().isAdmin()) {
                    menu = Optional.of(new UserListMenu(printStream, itemController, userController));
                } else {
                    menu = Optional.of(new UserProfileMenu(printStream));
                }
                break;
            case "0":
                exit();
                break;
            default:
                this.printStream.println("Please select a valid option!");
        }
        if (menu.isPresent()) {
            menu.get().inflate();
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
