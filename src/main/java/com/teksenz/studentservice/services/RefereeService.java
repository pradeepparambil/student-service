package com.teksenz.studentservice.services;

import com.teksenz.studentservice.repositories.RefereeRepository;
import com.teksenz.studentservice.web.model.RefereeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface RefereeService {
    RefereeDto saveNewReferee(RefereeDto refereeDto);
    void updateReferee(UUID id,RefereeDto refereeDto);
    RefereeDto findRefereeById(UUID uuid);
    List<RefereeDto> findAllReferees();
    void deleteReferee(UUID uuid);
}
