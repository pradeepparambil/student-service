package com.teksenz.studentservice.web.controllers;

import com.teksenz.studentservice.web.model.RefereeDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/student/referee/")
public class RefereeController {

    @PostMapping
    public void addNewReferee(@RequestBody RefereeDto refereeDto){

    }

}
