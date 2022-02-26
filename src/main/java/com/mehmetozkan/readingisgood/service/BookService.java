package com.mehmetozkan.readingisgood.service;

import com.mehmetozkan.readingisgood.entity.Book;
import com.mehmetozkan.readingisgood.exception.EntityNotExistException;
import com.mehmetozkan.readingisgood.exception.lackOfStockException;
import com.mehmetozkan.readingisgood.model.enums.Status;
import com.mehmetozkan.readingisgood.model.request.BookCreateRequest;
import com.mehmetozkan.readingisgood.model.request.BookStockUpdateRequest;
import com.mehmetozkan.readingisgood.model.request.BookUpdateRequest;
import com.mehmetozkan.readingisgood.model.response.BookResponse;
import com.mehmetozkan.readingisgood.repository.BookRepository;
import com.mehmetozkan.readingisgood.util.ModalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse createBook(BookCreateRequest request) {

        Book book = new Book();
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setWriter(request.getWriter());
        book.setPrice(request.getPrice());
        book.setRemainingStock(request.getRemainingStock());

        bookRepository.save(book);

        logger.info("Book saved successfully! {}", book);

        BookResponse response = new BookResponse();
        response.setStatus(Status.SUCCESS);
        response.setBook(ModalMapper.toDTO(book));

        return response;

    }

    public BookResponse updateBook(BookUpdateRequest request) {

        Book book = bookRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotExistException(String.valueOf(request.getId())));

        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setWriter(request.getWriter());
        book.setPrice(request.getPrice());
        book.setRemainingStock(request.getRemainingStock());

        bookRepository.save(book);

        logger.info("Book updated successfully! {}", book);

        BookResponse response = new BookResponse();
        response.setStatus(Status.SUCCESS);
        response.setBook(ModalMapper.toDTO(book));

        return response;
    }

    public BookResponse deleteBook(Long id) {

        BookResponse response = new BookResponse();

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistException(String.valueOf(id)));

        response.setBook(ModalMapper.toDTO(book));

        bookRepository.delete(book);

        logger.info("Book deleted successfully! {}", book);

        response.setStatus(Status.SUCCESS);

        return response;

    }

    public BookResponse updateBookStock(BookStockUpdateRequest request) {

        Book book = bookRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotExistException(String.valueOf(request.getId())));

        if (book.getRemainingStock() < request.getSoldStock())
            throw new lackOfStockException();

        book.setRemainingStock(book.getRemainingStock() - request.getSoldStock());

        bookRepository.save(book);

        logger.info("Book stock updated successfully! {}", book);

        BookResponse response = new BookResponse();
        response.setStatus(Status.SUCCESS);
        response.setBook(ModalMapper.toDTO(book));

        return response;
    }
}
