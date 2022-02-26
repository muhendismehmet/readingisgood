package com.mehmetozkan.readingisgood.model;

import com.mehmetozkan.readingisgood.model.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetail {

    private BookDTO book;
    private Long orderCount;

}
