package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.twu.biblioteca.TestHelper.getBooks;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BookControllerTest {


    @Test
    public void availableBooks_returnsOnlyNotCheckedOutBooks() throws IOException {
        BookRepository spyBookRepository = mock(BookRepository.class);
        ArrayList<Book> books = getBooks();
        books.get(0).setIsCheckout(true);
        when(spyBookRepository.getAll()).thenReturn(books);
        BookController controller = new BookController(spyBookRepository);

        controller.getAvailableBooks();

        assertThat(controller.getAvailableBooks().size(), is(getBooks().size() - 1));
    }

    @Test
    public void checkoutBook_whenNotCheckedOutBefore_returnsSuccessful() throws IOException {
        BookRepository spyBookRepository = mock(BookRepository.class);
        ArrayList<Book> books = getBooks();
        Book book = getBooks().get(0);
        assertThat(book.isCheckout(), is(false));
        when(spyBookRepository.getById(1)).thenReturn(book);

        BookController controller = new BookController(spyBookRepository);
        boolean checkOutSuccessful = controller.checkoutBook(1);

        assertThat(checkOutSuccessful, is(true));
    }

    @Test
     public void checkoutBook_whenAlreadyCheckedOut_returnsUnSuccessful() throws IOException {
         BookRepository spyBookRepository = mock(BookRepository.class);
         ArrayList<Book> books = getBooks();
         Book book = getBooks().get(0);
         book.setIsCheckout(true);
         assertThat(book.isCheckout(), is(true));
         when(spyBookRepository.getById(1)).thenReturn(book);

         BookController controller = new BookController(spyBookRepository);
         boolean checkOutSuccessful = controller.checkoutBook(1);

         assertThat(checkOutSuccessful, is(false));
     }

}
