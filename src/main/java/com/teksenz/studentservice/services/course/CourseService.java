package com.teksenz.studentservice.services.course;

import com.teksenz.studentservice.services.course.model.CourseDto;
import com.teksenz.studentservice.services.course.model.CourseState;

import java.util.Optional;

public interface CourseService {
    Optional<CourseDto> findCourseToBeEnrolled();
}
