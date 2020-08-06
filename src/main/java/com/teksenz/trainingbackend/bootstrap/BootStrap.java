package com.teksenz.trainingbackend.bootstrap;

import com.teksenz.trainingbackend.domain.Referee;
import com.teksenz.trainingbackend.domain.Student;
import com.teksenz.trainingbackend.repositories.RefereeRepository;
import com.teksenz.trainingbackend.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor
public class BootStrap implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final RefereeRepository refereeRepository;
    @Override
    public void run(String... args) throws Exception {
        if(studentRepository.count() == 0){
            loadStudents();
        }
    }

    private void loadStudents() {

        Student student1 = Student.builder()
                .firstName("Bettin")
                .lastName("Jacob")
                .email("bettin.jacob@gmail.com")
                .phoneNo("3452343243")
                .qualification("Btech")
                .build();

        Student student2 = Student.builder()
                .firstName("Priya")
                .lastName("Raman")
                .email("priya.raman@gmail.com")
                .phoneNo("43543533")
                .qualification("BE Computer Science")
                .build();

        Student student3 = Student.builder()
                .firstName("Maria")
                .lastName("John")
                .email("maria.john@gmail.com")
                .phoneNo("67575453")
                .qualification("Btech")
                .build();

        Referee referee1 = Referee.builder()
                .firstName("Sarath")
                .lastName("Prasad")
                .email("sarath.prasad@gmail.com")
                .phone("2342342342")
                .build();

        Referee referee2 = Referee.builder()
                .firstName("Nanda")
                .lastName("Gopan")
                .email("nanda.gopan@gmail.com")
                .phone("5674534534")
                .build();

        student1.setReferee(referee1);
        student2.setReferee(referee1);
        student3.setReferee(referee2);

        List<Student> studentList1 = new ArrayList<>();
        List<Student> studentList2 = new ArrayList<>();

        studentList1.add(student1);
        studentList1.add(student2);

        studentList2.add(student3);

        referee1.setStudents(studentList1);
        referee2.setStudents(studentList2);


        refereeRepository.saveAndFlush(referee1);
        refereeRepository.saveAndFlush(referee2);

        System.out.println("Student Count =====>" + studentRepository.count());

        System.out.println("Referee Count =====>" + refereeRepository.count());
    }
}
