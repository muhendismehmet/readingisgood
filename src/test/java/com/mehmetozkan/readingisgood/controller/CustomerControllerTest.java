package com.mehmetozkan.readingisgood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mehmetozkan.readingisgood.model.request.CustomerCreateRequest;
import com.mehmetozkan.readingisgood.model.request.CustomerParameterRequest;
import com.mehmetozkan.readingisgood.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"dev"})
@AutoConfigureMockMvc
@AutoConfigureJson
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks //@Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @BeforeEach
    void initialize() {

    }

    @Test
    public void it_should_invoke_api_customer_create_endpoint() throws Exception {

        CustomerCreateRequest request = new CustomerCreateRequest();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(post("/api/customer").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                        .content(requestBody))
                .andExpect(status().isBadRequest()).andReturn();

    }

    @Test
    public void it_should_invoke_api_customer_get_endpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/customer/{id}", 1)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void it_should_invoke_api_customer_order_endpoint() throws Exception {

        CustomerParameterRequest request = new CustomerParameterRequest();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(post("/api/customer/order")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody))
                .andExpect(status().isBadRequest()).andReturn();

    }
}
