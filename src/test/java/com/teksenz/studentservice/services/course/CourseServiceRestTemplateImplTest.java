package com.teksenz.studentservice.services.course;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
@Disabled
@SpringBootTest
class CourseServiceRestTemplateImplTest {
    @Autowired
    CourseService courseService;

    @Test
    void findCourseToBeEnrolled() {
        assertTrue(courseService.findCourseToBeEnrolled().isPresent());
    }

}