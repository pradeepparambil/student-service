package com.teksenz.studentservice.services.course;

import com.teksenz.studentservice.services.StudentService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Disabled
@SpringBootTest
class CourseServiceImplTest {
    @Autowired
    CourseService courseService;

    @Test
    void findCourseToBeEnrolled() {
        assertTrue(courseService.findCourseToBeEnrolled().isPresent());
    }

}