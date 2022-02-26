package com.mehmetozkan.readingisgood.controller;

import com.mehmetozkan.readingisgood.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StaticticsController staticticsController;

    @MockBean
    private StatisticsService statisticsService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(staticticsController).isNotNull();
    }

    @Test
    public void it_should_invoke_customer_Statistics_by_id() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/statistics/{id}", 1)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("mehmet", "password"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
