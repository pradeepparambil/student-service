package com.teksenz.studentservice.repositories;

import com.teksenz.studentservice.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    Student getStudent(){
        return Student.builder()
                .firstName("Bettin")
                .lastName("Jacob")
                .email("bettin.jacob@gmail.com")
                .phoneNo("3452343243")
                .build();
    }
    @Test
    @DisplayName("Save a student and retrieve the same")
    void testStudentSaveAndRetrive() {
        Student student = getStudent();
        studentRepository.save(student);
        assertEquals(1,studentRepository.count());
    }

    @Test
    void findByFirstName() {
        Student student = getStudent();
        studentRepository.save(student);
        assertEquals(studentRepository.findByFirstName(student.getFirstName()).get().getFirstName(),student.getFirstName());

    }

    @Test
    void findByLastName() {
        Student student = getStudent();
        studentRepository.save(student);
        assertEquals(studentRepository.findByFirstName(student.getFirstName()).get().getLastName(),student.getLastName());
    }
}