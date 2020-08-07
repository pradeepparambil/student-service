package com.teksenz.studentservice.web.controllers;

import com.teksenz.studentservice.services.RefereeService;
import com.teksenz.studentservice.web.model.RefereeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/referee")
@RequiredArgsConstructor
public class RefereeController {
    private final RefereeService refereeService;
    @PostMapping(value = "/", consumes = {"application/json"})
    public ResponseEntity addNewReferee(@RequestBody RefereeDto refereeDto){
        RefereeDto savedDto = refereeService.saveNewReferee(refereeDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location","/api/v1/referee/"
                +savedDto.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}",consumes = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReferee(@PathVariable UUID id, @RequestBody RefereeDto refereeDto){
        refereeService.updateReferee(id,refereeDto);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReferee(@PathVariable UUID id){
        refereeService.deleteReferee(id);
    }
    @GetMapping(value = "/",produces = {"application/json"})
    public ResponseEntity<List<RefereeDto>> findAllReferees(){
        return new ResponseEntity<>(refereeService.findAllReferees(),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = {"application/json"})
    public ResponseEntity<RefereeDto> findAReferee(@PathVariable UUID id){
        return new ResponseEntity<>(refereeService.findRefereeById(id),HttpStatus.OK);
    }
}
