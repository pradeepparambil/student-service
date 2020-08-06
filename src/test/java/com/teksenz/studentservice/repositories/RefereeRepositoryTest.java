package com.teksenz.studentservice.repositories;

import com.teksenz.studentservice.domain.Referee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RefereeRepositoryTest {
    @Autowired
    private RefereeRepository refereeRepository;
    @BeforeEach
    void setUp() {
    }

    public Referee getReferee(){
        return Referee.builder()
                .firstName("John")
                .lastName("Welcher")
                .email("john.welcher@gmail.com")
                .phone("23423432")
                .build();
    }

    @Test
    public void saveReferee(){
        refereeRepository.save(getReferee());
        assertEquals(1,refereeRepository.count(),"Referee is not saved");
    }

    @Test
    void findByFirstName() {
        refereeRepository.save(getReferee());
        Referee referee = refereeRepository.findByFirstName("John").get();
        assertNotNull(referee);
    }
    @Test
    void updateReferee(){
        refereeRepository.save(getReferee());
        Referee referee = refereeRepository.findByFirstName("John").get();
        referee.setFirstName("Johney");
        refereeRepository.save(referee);
        Referee referee1 = refereeRepository.findByFirstName("Johney").get();
        assertEquals("Johney",referee1.getFirstName());
    }
    @Test
    void deleteReferee(){
        refereeRepository.save(getReferee());
        Referee referee = refereeRepository.findByFirstName("John").get();
        refereeRepository.deleteById(referee.getId());
        assertEquals(0,refereeRepository.count());
    }
}