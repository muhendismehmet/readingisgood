package com.mehmetozkan.readingisgood.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {

    @Test
    public void it_should_convert_to_string_properly() {

        Book book = new Book();
        book.setId(1L);
        book.setName("The Silmarillion");
        book.setDescription("The Silmarillion is a collection of mythopoeic stories.");
        book.setWriter("J. R. R. Tolkien");
        book.setPrice(new BigDecimal(15));
        book.setRemainingStock(10L);

        String bookStringValue = book.toString();

        assertThat(bookStringValue).isEqualTo("Book(id=1, version=0, name=The Silmarillion, description=The Silmarillion is a collection of mythopoeic stories., writer=J. R. R. Tolkien, price=15, remainingStock=10)");
        assertThat(1L).isEqualTo(book.getId());
    }
}
