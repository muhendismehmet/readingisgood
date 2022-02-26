package com.mehmetozkan.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mehmetozkan.readingisgood.model.request.OrderByDateRequest;
import com.mehmetozkan.readingisgood.model.request.OrderRequest;
import com.mehmetozkan.readingisgood.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"dev"})
@AutoConfigureMockMvc
@AutoConfigureJson
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;


    @Test
    public void it_should_invoke_api_order_create_endpoint() throws Exception {

        OrderRequest request = new OrderRequest();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(post("/api/order")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody))
                .andExpect(status().isBadRequest()).andReturn();

    }

    @Test
    public void it_should_invoke_api_order_get_endpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/order/{id}", 1)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void it_should_invoke_api_order_date_endpoint() throws Exception {

        OrderByDateRequest request = new OrderByDateRequest();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(post("/api/order/date").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                        .content(requestBody))
                .andExpect(status().isBadRequest()).andReturn();

    }
}
