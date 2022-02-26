package com.mehmetozkan.readingisgood.controller;

import com.mehmetozkan.readingisgood.model.request.BookCreateRequest;
import com.mehmetozkan.readingisgood.model.request.BookStockUpdateRequest;
import com.mehmetozkan.readingisgood.model.request.BookUpdateRequest;
import com.mehmetozkan.readingisgood.model.response.BookResponse;
import com.mehmetozkan.readingisgood.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "#{@msProperties.book.path}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api
@SwaggerDefinition(tags = {@Tag(name = "getir-book-api", description = "Getir Book Api")
})
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService service) {
        this.bookService = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "create book", notes = "Create Book")
    public BookResponse createBook(@Valid @RequestBody BookCreateRequest request) {
        logger.info("Creating book started for request {}", request);
        return bookService.createBook(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update Book", notes = "Update Book")
    public BookResponse updateBook(@Valid @RequestBody BookUpdateRequest request) {
        logger.info("Updating book started for request: {}", request);
        return bookService.updateBook(request);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Book", notes = "Delete Book")
    public BookResponse deleteBook(@PathVariable Long id) {
        logger.info("Deleting book started for book id: {}", id);
        return bookService.deleteBook(id);
    }

    @PutMapping("/stock")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update Book Stock", notes = "Update Book Stock")
    public BookResponse updateBookStock(@Valid @RequestBody BookStockUpdateRequest request) {
        logger.info("Updating book stock started for request: {}", request);
        return bookService.updateBookStock(request);
    }
}
