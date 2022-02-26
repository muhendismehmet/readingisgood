package com.mehmetozkan.readingisgood.exception;

import com.mehmetozkan.readingisgood.constants.ApiErrorConstants;
import com.mehmetozkan.readingisgood.model.error.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

//@Slf4j
@ControllerAdvice
@Component
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error = new ApiError(ex.getMessage(), ApiErrorConstants.REQUEST_METHOD_NOT_SUPPORTED, status, LocalDateTime.now());
        logger.error("Request method not supported error occured. Exception detail: {}", error);
        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error = new ApiError(ex.getMessage(), ApiErrorConstants.HTTP_MEDIA_TYPE_NOT_SUPPORTED, status, LocalDateTime.now());
        logger.error("Http media type not supported error occured. Exception detail: {}", error);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EntityNotExistException.class)
    public ResponseEntity<Object> handleEntityNotExistException(EntityNotExistException ex) {
        ApiError error = new ApiError(ex.getMessage(), ApiErrorConstants.ENTITY_NOT_EXIST, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        logger.error("Entity does not exist. Exception detail: {}", error);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(lackOfStockException.class)
    public ResponseEntity<Object> handleStockCannotGreaterThanOldStockException(lackOfStockException ex) {
        ApiError error = new ApiError(ex.getMessage(), ApiErrorConstants.STOCK_CAN_NOT_GREATER, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        logger.error("Sold stock cannot greater than old stock exception. Exception detail: {}", error);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MailAlreadyTakenException.class)
    public ResponseEntity<Object> handleMailAlreadyTakenException(MailAlreadyTakenException ex) {
        ApiError error = new ApiError(ex.getMessage(), ApiErrorConstants.MAIL_ALREADY_TAKEN, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        logger.error("Sold stock cannot greater than old stock exception. Exception detail: {}", error);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(OrderCountInvalidException.class)
    public ResponseEntity<Object> handleOrderCountInvalidException(OrderCountInvalidException ex) {
        ApiError error = new ApiError(ex.getMessage(), ApiErrorConstants.ORDER_COUNT_INVALID, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        logger.error("Order count is invalid exception. Exception detail: {}", error);
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
