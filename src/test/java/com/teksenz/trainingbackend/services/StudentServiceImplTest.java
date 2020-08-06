package com.teksenz.trainingbackend.services;

import com.teksenz.trainingbackend.domain.Student;
import com.teksenz.trainingbackend.repositories.StudentRepository;
import com.teksenz.trainingbackend.web.mapper.StudentMapper;
import com.teksenz.trainingbackend.web.mapper.StudentMapperImpl;
import com.teksenz.trainingbackend.web.model.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private  StudentServiceImpl studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper = new StudentMapperImpl();


    Student getStudent(){
        return Student.builder()
                .firstName("Bettin")
                .lastName("Jacob")
                .email("bettin.jacob@gmail.com")
                .phoneNo("3452343243")
                .build();
    }
    StudentDto getStudentDto(){
        return StudentDto.builder()
                .firstName("Bettin")
                .lastName("Jacob")
                .email("bettin.jacob@gmail.com")
                .phoneNo("3452343243")
                .build();
    }
    @Test
    void save() {
        Student student = getStudent();
        StudentDto studentDto = getStudentDto();
        when(studentRepository.save(any())).thenReturn(student);
        when(studentMapper.studentToDto(any())).thenReturn(getStudentDto());
        when(studentMapper.dtoToStudent(any())).thenReturn(getStudent());

        StudentDto savedStudent = studentService.save(studentDto);
        verify(studentRepository).save(any());
        verify(studentMapper).dtoToStudent(any());
        verify(studentMapper).studentToDto(any());
        assertNotNull(savedStudent);

    }

    @Test
    void findAll() {
        List<Student> students = new ArrayList<>();
        students.add(getStudent());
        students.add(getStudent());
        when(studentRepository.findAll()).thenReturn(students);
        List<StudentDto> studentsDto = studentService.findAll();
        assertEquals(studentsDto.size(),2);
        verify(studentRepository).findAll();

    }

    @Test
    void updateById() {
        UUID id = UUID.randomUUID();
        Student student = getStudent();
        StudentDto studentDto = getStudentDto();
        student.setId(id);
        studentDto.setId(id);
        when(studentRepository.findById(any())).thenReturn(Optional.of(student));
        when(studentRepository.save(any())).thenReturn(student);
        when(studentMapper.studentToDto(any())).thenReturn(studentDto);
        when(studentMapper.dtoToStudent(any())).thenReturn(student);
        StudentDto updatedStudent = studentService.updateById(id,studentDto);
        assertNotNull(updatedStudent);
        verify(studentRepository).save(any());
        verify(studentMapper).dtoToStudent(any());
        verify(studentMapper).studentToDto(any());
    }

    @Test
    void deleteByIdSuccess() {
        UUID id = UUID.randomUUID();
        Student student = getStudent();
        StudentDto studentDto = getStudentDto();
        student.setId(id);
        studentDto.setId(id);
        when(studentRepository.findById(any())).thenReturn(Optional.of(student));
        studentService.deleteById(id);
        verify(studentRepository).findById(id);
    }
    @Test
    void deleteByIdFailure() {
        UUID id = UUID.randomUUID();
        Student student = getStudent();
        StudentDto studentDto = getStudentDto();
        student.setId(id);
        studentDto.setId(id);
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,()->studentService.deleteById(id));
        verify(studentRepository).findById(id);
    }
}