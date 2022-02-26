package com.mehmetozkan.readingisgood.model.error;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiErrorTest {

    @Test
    public void it_should_convert_to_string_properly() {

        String now = "1995-06-07 10:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);

        ApiError apiError = new ApiError("exceptionMessage", "description", HttpStatus.I_AM_A_TEAPOT, formatDateTime);
        apiError.setExceptionMessage("exceptionMessage");
        apiError.setDescription("description");
        apiError.setStatus(HttpStatus.I_AM_A_TEAPOT);
        apiError.setTimestamp(formatDateTime);

        assertThat(HttpStatus.I_AM_A_TEAPOT).isEqualTo(apiError.getStatus());

    }
}
