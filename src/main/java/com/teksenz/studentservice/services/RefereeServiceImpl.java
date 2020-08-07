package com.teksenz.studentservice.services;

import com.teksenz.studentservice.repositories.RefereeRepository;
import com.teksenz.studentservice.web.controllers.NotFoundException;
import com.teksenz.studentservice.web.mapper.RefereeMapper;
import com.teksenz.studentservice.web.model.RefereeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {
    private final RefereeRepository refereeRepository;
    private final RefereeMapper refereeMapper;
    @Override
    public RefereeDto saveNewReferee(RefereeDto refereeDto) {
        return refereeMapper.refereeToDto(refereeRepository.save(refereeMapper.dtoToReferee(refereeDto)));
    }

    @Override
    public void updateReferee(UUID id, RefereeDto refereeDto) {
        refereeRepository.findById(id).orElseThrow(()->{throw new NotFoundException("Referee not found");});
        refereeRepository.save(refereeMapper.dtoToReferee(refereeDto));
    }

    @Override
    public RefereeDto findRefereeById(UUID id) {
        return refereeMapper.refereeToDto(refereeRepository.findById(id)
                .orElseThrow(()->{throw new NotFoundException("Referee not found");}));
    }

    @Override
    public List<RefereeDto> findAllReferees() {
        return refereeRepository.findAll().stream().map(refereeMapper::refereeToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteReferee(UUID id) {
        refereeRepository.findById(id).orElseThrow(()->{throw new NotFoundException("Referee not found");});
        refereeRepository.deleteById(id);
    }
}
