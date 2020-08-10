package com.teksenz.studentservice.repositories;

import com.teksenz.studentservice.domain.Referee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefereeRepository extends JpaRepository<Referee,UUID> {
    Optional<Referee> findByFirstName(String firstName);



}
