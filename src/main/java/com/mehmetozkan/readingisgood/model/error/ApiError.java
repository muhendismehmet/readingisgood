package com.mehmetozkan.readingisgood.model.error;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String exceptionMessage;
    private String description;
    HttpStatus status;
    LocalDateTime timestamp;
}
