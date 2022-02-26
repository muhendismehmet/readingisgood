package com.mehmetozkan.readingisgood.model.response;

import com.mehmetozkan.readingisgood.model.dto.BookDTO;
import com.mehmetozkan.readingisgood.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookResponse {

    private BookDTO book;
    private Status status;
}
