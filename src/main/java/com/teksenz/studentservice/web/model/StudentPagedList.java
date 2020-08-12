package com.teksenz.studentservice.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.teksenz.studentservice.domain.Student;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class StudentPagedList extends PageImpl<StudentDto> {

    public StudentPagedList(List<StudentDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public StudentPagedList(List<StudentDto> content) {
        super(content);
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public StudentPagedList(@JsonProperty("content") List<StudentDto> content,
                             @JsonProperty("number") int number,
                             @JsonProperty("size") int size,
                             @JsonProperty("totalElements") Long totalElements,
                             @JsonProperty("pageable") JsonNode pageable,
                             @JsonProperty("last") boolean last,
                             @JsonProperty("totalPages") int totalPages,
                             @JsonProperty("sort") JsonNode sort,
                             @JsonProperty("first") boolean first,
                             @JsonProperty("numberOfElements") int numberOfElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }
}
