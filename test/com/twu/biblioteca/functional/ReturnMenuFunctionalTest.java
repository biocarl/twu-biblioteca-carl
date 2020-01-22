package com.twu.biblioteca.functional;

import com.twu.biblioteca.BookController;
import com.twu.biblioteca.SpyPrintStream;
import com.twu.biblioteca.ui.ReturnMenu;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static com.twu.biblioteca.TestHelper.getInMemoryDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ReturnMenuFunctionalTest {
    BookController bookController;

    @Before
    public void setUp() {
        bookController = new BookController(getInMemoryDatabase());
    }

    @Test
    public void returnBook_whenBeforeCheckedOutBook_isAvailableAgainInBooks() throws IOException {
        final int bookId = 1;
        bookController.checkoutBook(bookId);
        final String checkedOutBookTitle = bookController.getById(bookId).getTitle();
        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn(checkedOutBookTitle); //Input name of checked-out book
        assertThat(false,  is (bookController.getAvailableBooks().stream().anyMatch(e->e.getTitle().equals(checkedOutBookTitle))));

        ReturnMenu returnMenu = new ReturnMenu(spyPrintStream,bufferedReader,bookController);
        returnMenu.inflate();

        assertThat(true,  is (bookController.getAvailableBooks().stream().anyMatch(e->e.getTitle().equals(checkedOutBookTitle))));
    }


    @Test
    public void returnBook_whenValidBookTitleAndCheckedOutBook_isReturningSuccessMessage() throws IOException {
        final int bookId = 1;
        bookController.checkoutBook(bookId);
        final String checkedOutBookTitle = bookController.getById(bookId).getTitle();

        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn(checkedOutBookTitle); //Input name of checked-out book

        ReturnMenu returnMenu = new ReturnMenu(spyPrintStream,bufferedReader,bookController);
        returnMenu.inflate();

        assertThat(spyPrintStream.printedStrings().get(0), is("Thank you for returning the book"));
    }

    @Test
    public void returnBook_whenInValidBookTitle_isReturningFailureMessage() throws IOException {
        final int bookId = 1;
        bookController.checkoutBook(bookId);
        final String checkedOutBookTitle = bookController.getById(bookId).getTitle();

        SpyPrintStream spyPrintStream = new SpyPrintStream(System.out);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn(checkedOutBookTitle+"ABC"); //Input name of checked-out book

        ReturnMenu returnMenu = new ReturnMenu(spyPrintStream,bufferedReader,bookController);
        returnMenu.inflate();

        assertThat(spyPrintStream.printedStrings().get(0), is("This is not a valid book to return."));
    }

}
