package com.teksenz.studentservice.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teksenz.studentservice.services.RefereeService;
import com.teksenz.studentservice.web.model.RefereeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RefereeController.class)
class RefereeControllerTest {
    private final String baseUrl = "/api/v1/student/referee/";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RefereeService refereeService;

    public RefereeDto getRefereeDto(){
        return RefereeDto.builder()
                .firstName("John")
                .lastName("Welcher")
                .email("john.welcher@gmail.com")
                .phone("23423432")
                .build();
    }

    @Test
    void addNewReferee() throws Exception {
        RefereeDto refereeDtoSaved = getRefereeDto();
        refereeDtoSaved.setId(UUID.randomUUID());
        given(refereeService.saveNewReferee(any())).willReturn(refereeDtoSaved);
        MvcResult mvcResult = mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getRefereeDto())))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location",baseUrl+refereeDtoSaved.getId()))
//                .andExpect(content().string("expected content"))//Just for reference
                .andReturn();
//        Just another option
        assert(mvcResult.getResponse().getHeader("Location"))
                .equalsIgnoreCase(baseUrl+refereeDtoSaved.getId());
    }

    @Test
    void updateReferee() throws Exception {
        mockMvc.perform(put(baseUrl+UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getRefereeDto())))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteReferee() throws Exception {
        mockMvc.perform(delete(baseUrl+UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }

    @Test
    void findAllReferees() throws Exception {
        given(refereeService.findAllReferees()).willReturn(Arrays.asList(getRefereeDto()));
        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findAReferee() throws Exception {
        given(refereeService.findRefereeById(any())).willReturn(getRefereeDto());
        mockMvc.perform(get(baseUrl+UUID.randomUUID()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}