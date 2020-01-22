package com.twu.biblioteca.functional;

import com.twu.biblioteca.BookRepository;
import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.BookController;
import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.ui.CheckoutMenu;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.twu.biblioteca.TestHelper.getBooks;
import static com.twu.biblioteca.TestHelper.getInMemoryDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CheckoutMenuFunctionalTest {
    BookController bookController;

    @Before
    public void setUp() {
        bookController = new BookController(getInMemoryDatabase());
    }

    @Test
    public void printBookOptions_printsAvailableBooksWithUniqueNumber() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);

        CheckoutMenu checkoutMenu = new CheckoutMenu(spyPrintStream, bufferedReader, bookController);
        checkoutMenu.printAllBooks();

        assertThat(spyPrintStream.printedStrings().get(0), is("1\t" + bookController.getById(1)));
        assertThat(spyPrintStream.printedStrings().get(1), is("2\t" + bookController.getById(2)));
    }

    @Test
    public void selectOption_whenSelectingBookForCheckout_bookIsCheckout() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select first book

        CheckoutMenu checkoutMenu = new CheckoutMenu(spyPrintStream, bufferedReader, bookController);
        assertThat(false, is(bookController.getById(1).isCheckout()));

        checkoutMenu.selectBook();

        assertThat(true, is(bookController.getById(1).isCheckout()));
    }

    @Test
    public void selectOption_whenSelectingBookAvailableBookForCheckout_positiveResponseMessageIsPrinted() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select first book

        CheckoutMenu checkoutMenu = new CheckoutMenu(spyPrintStream, bufferedReader, bookController);
        checkoutMenu.selectBook();

        assertThat(spyPrintStream.printedStrings().get(0), is("Thank you! Enjoy the book"));
    }

    @Test
    public void selectOption_whenSelectingBookNotAvailableForCheckout_negativeResponseMessageIsPrinted() throws IOException {
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1"); //select first book

        BookController controller = mock(BookController.class);
        when(controller.getAllBooks()).thenReturn(getBooks());
        when(controller.checkoutBook(1)).thenReturn(false);

        CheckoutMenu checkoutMenu = new CheckoutMenu(spyPrintStream, bufferedReader, controller);
        checkoutMenu.selectBook();

        assertThat(spyPrintStream.printedStrings().get(0), is("Sorry, that book is not available"));
    }
}
