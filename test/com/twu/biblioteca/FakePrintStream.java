package com.twu.biblioteca;

import java.io.OutputStream;
import java.io.PrintStream;

public class FakePrintStream extends PrintStream {
    private String printedString;

    public FakePrintStream(OutputStream out) {
        super(out);
    }

    @Override
    public void println(String string) {
        printedString = string;
    }

    public String printedString() {
        return printedString;
    }
}

