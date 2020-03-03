package com.search.service.controllers;

import com.google.common.base.Joiner;
import com.search.service.beans.Course;
import com.search.service.beans.Student;
import com.search.service.common.PersonSpecification;
import com.search.service.common.SearchOperation;
import com.search.service.common.StudentSpecificationsBuilder;
import com.search.service.service.CourseService;
import com.search.service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;


    @GetMapping(path = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        Optional<Student> studentObj = studentService.getByStudentId(id);
        if (!studentObj.isEmpty()) {
            return new ResponseEntity<>(studentObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Data not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getStudentAll() {
        List<Student> studentObj = studentService.getAllStudents();
        if (!studentObj.isEmpty()) {
            return new ResponseEntity<>(studentObj, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/course/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCourseById(@PathVariable Long id) {
        Optional<Course> courseObj = courseService.getByCourseId(id);
        if (!courseObj.isEmpty()) {
            return new ResponseEntity<>(courseObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Data not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/course", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> getCourseAll() {
        List<Course> courseObj = courseService.getAllCourse();
        if (!courseObj.isEmpty()) {
            return new ResponseEntity<>(courseObj, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @GetMapping(path="/getStudent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Student>> getStudent(@RequestParam(value = "search") String search) {

       StudentSpecificationsBuilder builder = new StudentSpecificationsBuilder();
       String operationSetExper = Joiner.on("|")
               .join(SearchOperation.SIMPLE_OPERATION_SET);
       Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
       Matcher matcher = pattern.matcher(search + ",");
       while (matcher.find()) {
           builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
       }
       Specification s1 = builder.build();
       List<Student> list= studentService.getStudent(s1);
       return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(path="/getStudents", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Student>> getStudentIn(@RequestParam(value = "search") String search) {

        Student filter= new Student();
        filter.setLastName(search);
        PersonSpecification s= new PersonSpecification(filter);
        Specification<Student> finalSpec = s
                .or((root, query, cb) -> root.get("lastName").in(Arrays.asList("sharma")));
        List<Student> list= studentService.getStudent(finalSpec);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}

