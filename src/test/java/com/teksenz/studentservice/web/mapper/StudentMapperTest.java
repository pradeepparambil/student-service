package com.teksenz.studentservice.web.mapper;

import com.teksenz.studentservice.domain.Student;
import com.teksenz.studentservice.web.model.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(MockitoExtension.class)
class StudentMapperTest {

    @InjectMocks
    private StudentMapperImpl studentMapper;
    @Mock
    private DateMapper dateMapper;
    @Mock
    private RefereeMapper refereeMapper;

    Student getStudent(){
        return Student.builder()
                .firstName("Bettin")
                .lastName("Jacob")
                .email("bettin.jacob@gmail.com")
                .phoneNo("3452343243")
                .build();
    }
    @Test
    void studentToDto() {
        Student student = getStudent();
        StudentDto studentDto = studentMapper.studentToDto(student);
        assertNotNull(studentDto);

    }

    @Test
    void dtoToStudent() {
        StudentDto studentDto = studentMapper.studentToDto(getStudent());
        assertNotNull(studentMapper.dtoToStudent(studentDto));

    }
}