package com.gyarsilalsolanki011.journeymate.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenTripNotFound_thenReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/trips/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Trip not found with id: 99"));
    }

    @Test
    void whenValidationFails_thenReturnsBadRequest() throws Exception {
        String invalidTripJson = """
            {
              "destination": "",
              "startDate": null,
              "endDate": null,
              "price": -50,
              "status": "ONGOING"
            }
            """;

        mockMvc.perform(post("/api/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidTripJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("validation error"))
                .andExpect(jsonPath("$.details.destination").exists())
                .andExpect(jsonPath("$.details.price").exists());
    }

    @Test
    void whenUnexpectedError_thenReturnsInternalServerError() throws Exception {
        mockMvc.perform(get("/api/trips/error"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("An unexpected error occurred"));
    }
}

