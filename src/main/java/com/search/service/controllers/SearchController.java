package com.search.service.controllers;

import com.search.service.beans.Course;
import com.search.service.beans.SearchStudentList;
import com.search.service.beans.Student;
import com.search.service.service.CourseService;
import com.search.service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Course>> getCourseAll(@RequestBody  SearchStudentList search) {
        List<Course> courseObj = courseService.getCourses( search);
        if (!courseObj.isEmpty()) {
            return new ResponseEntity<>(courseObj, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @GetMapping(path="/getStudent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Student>> getStudent(@RequestBody  SearchStudentList search) {

       List<Student> list= studentService.getStudents(search);
       return new ResponseEntity<>(list, HttpStatus.OK);
    }


}

