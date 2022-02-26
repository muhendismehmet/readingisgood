package com.mehmetozkan.readingisgood.model.dto;


import com.mehmetozkan.readingisgood.entity.Book;
import com.mehmetozkan.readingisgood.util.ModalMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BookLightDTOTest {

    @Test
    public void it_should_convert_to_string_properly() {

        Book book = new Book();
        book.setId(1L);
        book.setName("The Silmarillion");
        book.setDescription("The Silmarillion is a collection of mythopoeic stories.");
        book.setWriter("J. R. R. Tolkien");
        book.setPrice(new BigDecimal(15));
        book.setRemainingStock(10L);

        BookLightDTO dto = ModalMapper.toLightDTO(book);

        String bookStringValue = dto.toString();

        assertThat(1L).isEqualTo(book.getId());
    }
}
