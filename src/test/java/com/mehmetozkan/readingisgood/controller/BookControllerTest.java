package com.mehmetozkan.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mehmetozkan.readingisgood.model.request.BookCreateRequest;
import com.mehmetozkan.readingisgood.model.request.BookStockUpdateRequest;
import com.mehmetozkan.readingisgood.model.request.BookUpdateRequest;
import com.mehmetozkan.readingisgood.service.BookService;
import com.mehmetozkan.readingisgood.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"dev"})
@AutoConfigureMockMvc
@AutoConfigureJson
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;


    @BeforeEach
    void initialize() {

    }

    @Test
    public void it_should_invoke_api_book_create_endpoint() throws Exception {

        BookCreateRequest request = new BookCreateRequest();
        request.setName("");
        request.setDescription("");
        request.setWriter("");
        request.setRemainingStock(0L);
        request.setPrice(new BigDecimal(0));

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        this.mockMvc.perform(post("/api/book")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.objectToJson(request))).andDo(print())
                        .andExpect(status().isBadRequest());

    }

    @Test
    public void it_should_invoke_api_book_update_endpoint() throws Exception {

        BookUpdateRequest request = new BookUpdateRequest();
        request.setName("");
        request.setDescription("");
        request.setWriter("");
        request.setRemainingStock(0L);
        request.setPrice(new BigDecimal(0));

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(put("/api/book")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                .contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isBadRequest()).andReturn();

    }

    @Test
    public void it_should_invoke_api_book_delete_endpoint() throws Exception {

        mockMvc.perform(delete("/api/book/1").contentType(MediaType.ALL_VALUE)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password")))

                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public void it_should_thrown_http_media_type_not_supported_exception() throws Exception {

        BookCreateRequest request = new BookCreateRequest();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(post("/api/book")
                        .contentType(MediaType.TEXT_HTML)
                        .content(requestBody)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password")))

                .andExpect(status().isUnsupportedMediaType())
                .andReturn();
    }

    @Test
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void it_should_throw_http_request_method_not_supported_exception() throws Exception {

        mockMvc.perform(get("/api/book").with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password")))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();

    }

    @Test
    public void it_should_invoke_api_book_stock_update_endpoint() throws Exception {

        BookStockUpdateRequest request = new BookStockUpdateRequest();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(put("/api/book/stock")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody))
                .andExpect(status().isBadRequest()).andReturn();

    }

}
