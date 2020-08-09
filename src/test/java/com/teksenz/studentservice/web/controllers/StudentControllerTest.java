package com.teksenz.studentservice.web.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teksenz.studentservice.services.StudentService;
import com.teksenz.studentservice.web.model.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl = "/api/v1/student/";
    @MockBean
    private StudentService studentService;
    private String studentJsonString;
    private StudentDto studentDto;


    @BeforeEach
    private void getStudent() throws JsonProcessingException {
        studentDto =  StudentDto.builder()
                .firstName("Bettin")
                .lastName("Jacob")
                .email("bettin.jacob@gmail.com")
                .phoneNo("3452343243")
                .qualification("BTech")
                .build();
        studentJsonString = objectMapper.writeValueAsString(studentDto);

    }

    @Test
    void saveStudent() throws Exception {
        studentDto.setId(UUID.randomUUID());
        given(studentService.save(any())).willReturn(studentDto);
        mockMvc.perform(post(baseUrl+"enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJsonString)
        ).andExpect(status().isCreated());
    }

    @Test
    void findAll() throws Exception {
        List<StudentDto> dtos = new ArrayList<>();
        dtos.add(studentDto);
        given(studentService.findAll()).willReturn(dtos);
        mockMvc.perform(get(baseUrl)).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        studentDto.setId(UUID.randomUUID());
        given(studentService.findById(any())).willReturn(studentDto);
        mockMvc.perform(get(baseUrl+UUID.randomUUID())).andExpect(status().isOk());
    }

    @Test
    void updateStudent() throws Exception {
        studentDto.setId(UUID.randomUUID());
        given(studentService.updateById(UUID.randomUUID(),studentDto)).willReturn(studentDto);
        mockMvc.perform(put(baseUrl+UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJsonString))
                .andExpect(status().isOk());
    }

    @Test
    void deleteStudent() throws Exception {
        given(studentService.findById(any())).willReturn(studentDto);
        mockMvc.perform(delete(baseUrl+UUID.randomUUID())).andExpect(status().isNoContent());
    }

    @Test
    void validateFieldsAgainstNull() throws Exception {
        StudentDto invalidStudent = StudentDto.builder().build();
        MvcResult mvcResult = mockMvc.perform(post(baseUrl+"enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidStudent)))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}