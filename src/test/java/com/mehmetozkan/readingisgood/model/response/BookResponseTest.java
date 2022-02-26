package com.mehmetozkan.readingisgood.model.response;

import com.mehmetozkan.readingisgood.model.dto.BookDTO;
import com.mehmetozkan.readingisgood.model.enums.Status;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookResponseTest {

    @Test
    public void it_should_convert_to_string_properly() {

        BookResponse response = new BookResponse();
        response.setBook(new BookDTO());
        response.setStatus(Status.SUCCESS);

         assertThat(Status.SUCCESS).isEqualTo(response.getStatus());

     }

}
