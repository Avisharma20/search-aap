package com.search.service.service.impl;

import com.search.service.beans.Course;
import com.search.service.beans.SearchSpec;
import com.search.service.beans.SearchStudentList;
import com.search.service.common.CourseSpecificationsBuilder;
import com.search.service.repository.CourseRepository;
import com.search.service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    @Override
    public List<Course> getCourses(SearchStudentList spec) {
        CourseSpecificationsBuilder builder = new CourseSpecificationsBuilder();
        List l =spec.getSearchSpecList();
        for(int i=0; i<l.size(); i++){
            SearchSpec s = (SearchSpec) l.get(i);
            builder.with(s.getIsPredicate(),s.getKey(),s.getOps(),s.getValue(),s.getPrefix(),s.getSuffix());
        }
        Specification s1 = builder.build();
        return courseService.findAll(s1);
    }
}
