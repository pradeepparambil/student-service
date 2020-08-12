package com.teksenz.studentservice.repositories;

import com.teksenz.studentservice.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByFirstName(String firstName);
    Optional<Student> findByLastName(String lastName);
    Page<Student> findAllByFirstName(String firstName, Pageable pageable);
}
