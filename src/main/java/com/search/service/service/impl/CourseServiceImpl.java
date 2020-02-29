package com.search.service.service.impl;

import com.search.service.beans.Course;
import com.search.service.repository.CourseRepository;
import com.search.service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseService;

    @Override
    public List<Course> getAllCourse() {
        return (List<Course>) courseService.findAll();
    }

    @Override
    public Optional<Course> getByCourseId(Long courseCd) {
         return courseService.findById(courseCd);
    }

    @Override
    public Course registerCourse(Course course) {
        return courseService.save(course);
    }
}
