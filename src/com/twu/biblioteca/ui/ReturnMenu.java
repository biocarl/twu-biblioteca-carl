package com.twu.biblioteca.ui;

import com.twu.biblioteca.ItemController;
import com.twu.biblioteca.domain.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ReturnMenu {

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;
    private final ItemController itemController;

    public ReturnMenu(PrintStream printStream, BufferedReader bufferedReader, ItemController itemController) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.itemController = itemController;
    }

    public void inflate() throws IOException {
        final String item = bufferedReader.readLine();
        final boolean itemReturnSuccessful = this.itemController.returnItem(item);
        if(itemReturnSuccessful){
            this.printStream.println("Thank you for returning the "+ itemController.getItemType());
        }else{
            this.printStream.println(String.format("This is not a valid %s to return.",itemController.getItemType()));
        }
    }
}
