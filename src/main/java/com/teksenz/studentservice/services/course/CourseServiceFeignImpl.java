package com.teksenz.studentservice.services.course;

import com.teksenz.studentservice.services.course.model.CourseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
@Service
public class CourseServiceFeignImpl implements CourseService{
    private final CourseServiceFeignClient courseServiceFeignClient;
    @Override
    public Optional<CourseDto> findCourseToBeEnrolled() {
        log.debug("Calling course-service");
        return courseServiceFeignClient.findCourseToBeEnrolled();
    }
}
