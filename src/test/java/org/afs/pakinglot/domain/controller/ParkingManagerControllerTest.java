package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.ParkingManagerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingManagerController.class)
class ParkingManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void givenParkingManagerController_whenGetAllParkingLots_thenReturnsAllParkingLots() throws Exception {
        mockMvc.perform(get("/parkingManager/allParkingLots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("The Plaza Park"))
                .andExpect(jsonPath("$[0].capacity").value(9))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("City Mall Garage"))
                .andExpect(jsonPath("$[1].capacity").value(12))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Office Tower Parking"))
                .andExpect(jsonPath("$[2].capacity").value(9));
    }

    @Test
    void givenParkingManagerController_whenPark_thenReturnsTicket() throws Exception {
        mockMvc.perform(post("/parkingManager/park")
                        .param("plateNumber", "ABC123")
                        .param("strategy", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plateNumber").value("ABC123"))
                .andExpect(jsonPath("$.parkingLot").exists())
                .andExpect(jsonPath("$.position").exists());
    }
}