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
}
