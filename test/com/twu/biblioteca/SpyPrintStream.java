package com.twu.biblioteca;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SpyPrintStream extends PrintStream {
    private ArrayList<String> printedStrings = new ArrayList<>();

    public SpyPrintStream(OutputStream out) {
        super(out);
    }

    @Override
    public void println(String string) {
        printedStrings.add(string);
    }

    public List<String> printedStrings() {
        return printedStrings;
    }

}

