package com.mehmetozkan.readingisgood.service;

import com.mehmetozkan.readingisgood.entity.Book;
import com.mehmetozkan.readingisgood.exception.EntityNotExistException;
import com.mehmetozkan.readingisgood.exception.lackOfStockException;
import com.mehmetozkan.readingisgood.model.request.BookCreateRequest;
import com.mehmetozkan.readingisgood.model.request.BookStockUpdateRequest;
import com.mehmetozkan.readingisgood.model.request.BookUpdateRequest;
import com.mehmetozkan.readingisgood.model.response.BookResponse;
import com.mehmetozkan.readingisgood.repository.BookRepository;
import com.mehmetozkan.readingisgood.util.ModalMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest(classes = {BookService.class})
@ActiveProfiles("dev")
@Import({EntityNotExistException.class, lackOfStockException.class})
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void it_should_save_book() {

        BookCreateRequest request = new BookCreateRequest();
        request.setName("name");
        request.setDescription("description");
        request.setWriter("writer");
        request.setPrice(new BigDecimal(1));
        request.setRemainingStock(1L);

        bookService.createBook(request);

    }

    @Test
    public void it_should_update_book() {

        Book book = new Book();
        book.setId(1L);

        when(bookRepository.findById(book.getId())).thenReturn(java.util.Optional.of(book));

        BookUpdateRequest request = new BookUpdateRequest();
        request.setId(1L);
        request.setName("name");
        request.setDescription("description");
        request.setWriter("writer");
        request.setPrice(new BigDecimal(1));
        request.setRemainingStock(1L);

        BookResponse response = new BookResponse();
        when(bookService.updateBook(request)).thenReturn(response);
    }

    @Test
    public void it_should_delete_book() {

        Book book = new Book();
        book.setId(1L);
        book.setPrice(new BigDecimal(1));

        when(bookRepository.findById(book.getId())).thenReturn(java.util.Optional.of(book));

        bookService.deleteBook(book.getId());
    }

    @ExceptionHandler(EntityNotExistException.class)
    public void it_should_thrown_entity_not_exist_exception() throws Exception {

        Book book = new Book();
        book.setId(1L);

        when(bookRepository.findById(book.getId())).thenThrow(new EntityNotExistException(String.valueOf(book.getId())));
        BookResponse response = new BookResponse();
        when(bookService.deleteBook(book.getId())).thenReturn(response);

    }

    @Test
    public void it_should_update_book_stock() {

        Book book = new Book();
        book.setId(1L);
        book.setRemainingStock(50L);
        book.setPrice(new BigDecimal(1));

        when(bookRepository.findById(book.getId())).thenReturn(java.util.Optional.of(book));

        BookStockUpdateRequest request = new BookStockUpdateRequest();
        request.setId(1L);
        request.setSoldStock(10L);

        bookService.updateBookStock(request);

    }

    @ExceptionHandler(lackOfStockException.class)
    public void it_should_update_book_stock_and_thrown_exception() {

        Book book = new Book();
        book.setId(1L);
        book.setRemainingStock(5L);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        BookStockUpdateRequest request = new BookStockUpdateRequest();
        request.setId(1L);
        request.setSoldStock(10L);

        BookResponse response = new BookResponse();
        response.setBook(ModalMapper.toDTO(book));
        when(bookService.updateBookStock(request)).thenReturn(response);


        assertEquals(response.getBook().getId(), book.getId());

    }
}
