package com.teksenz.studentservice.services;

import com.teksenz.studentservice.domain.Referee;
import com.teksenz.studentservice.repositories.RefereeRepository;
import com.teksenz.studentservice.web.mapper.RefereeMapper;
import com.teksenz.studentservice.web.model.RefereeDto;
import com.teksenz.studentservice.web.model.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RefereeServiceImplTest {
    @InjectMocks
    private RefereeServiceImpl refereeService;
    @Mock
    private RefereeRepository refereeRepository;
    @Mock
    private RefereeMapper refereeMapper;

    public RefereeDto getRefereeDto(){
        return RefereeDto.builder()
                .firstName("John")
                .lastName("Welcher")
                .email("john.welcher@gmail.com")
                .phone("23423432")
                .build();
    }
    public Referee getReferee(){
        return Referee.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("Welcher")
                .email("john.welcher@gmail.com")
                .phone("23423432")
                .build();
    }

    @Test
    void saveNewReferee() {
        when(refereeRepository.save(any())).thenReturn(getReferee());
        when(refereeMapper.dtoToReferee(any())).thenReturn(getReferee());
        when(refereeMapper.refereeToDto(any())).thenReturn(getRefereeDto());

        RefereeDto savedReferee = refereeService.saveNewReferee(getRefereeDto());
        assertNotNull(savedReferee);
    }

    @Test
    void updateReferee() {
        when(refereeMapper.dtoToReferee(any())).thenReturn(getReferee());
        when(refereeRepository.findById(any(UUID.class))).thenReturn(Optional.of(getReferee()));

        refereeService.updateReferee(UUID.randomUUID(),getRefereeDto());
        verify(refereeRepository).save(any());
        verify(refereeMapper).dtoToReferee(any());
    }

    @Test
    void findRefereeById() {
        when(refereeRepository.findById(any(UUID.class))).thenReturn(Optional.of(getReferee()));
        when(refereeMapper.refereeToDto(any())).thenReturn(getRefereeDto());
        RefereeDto refereeDtoFound = refereeService.findRefereeById(UUID.randomUUID());
        assertNotNull(refereeDtoFound);
    }

    @Test
    void findAllReferees() {
        when(refereeRepository.findAll()).thenReturn(Arrays.asList(getReferee()));
        when(refereeMapper.refereeToDto(any())).thenReturn(getRefereeDto());
        List<RefereeDto> refereeDtos = refereeService.findAllReferees();
        assertEquals(1,refereeDtos.size());
    }

    @Test
    void deleteReferee() {
        when(refereeRepository.findById(any(UUID.class))).thenReturn(Optional.of(getReferee()));
        refereeService.deleteReferee(UUID.randomUUID());
        verify(refereeRepository).deleteById(any());
    }
}