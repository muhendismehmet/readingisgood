package com.mehmetozkan.readingisgood.model.request;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderByDateRequestTest {


    @Test
    public void it_should_convert_to_string_properly() {

        OrderByDateRequest request = new OrderByDateRequest();
        request.setEndDate(new Date(1995, Calendar.JULY,7));
        request.setStartDate(new Date(1995, Calendar.JULY,7));

        String requestStringValue = request.toString();

        assertThat(new Date(1995, Calendar.JULY,7)).isEqualTo(request.getStartDate());

     }

}
