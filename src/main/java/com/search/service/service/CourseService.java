package com.search.service.service;

import com.search.service.beans.Course;
import com.search.service.beans.SearchStudentList;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    public List<Course> getAllCourse();

    public Optional<Course> getByCourseId(Long courseCd);
    public List<Course> getCourses(SearchStudentList spec);
    public Course registerCourse(Course course);
}
