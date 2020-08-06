package com.teksenz.trainingbackend.repositories;

import com.teksenz.trainingbackend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByFirstName(String firstName);
    Optional<Student> findByLastName(String lastName);
}
