package com.teksenz.studentservice.services.course;

import com.teksenz.studentservice.services.course.model.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "course-service")
public interface CourseServiceFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = CourseServiceRestTemplateImpl.COURSE_PATH)
    Optional<CourseDto> findCourseToBeEnrolled();

}
