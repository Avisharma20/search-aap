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

@RestController
@RequestMapping("/register")
public class RegisterEntity{
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @PostMapping(path="/createCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Course> createOrSaveStudent(@RequestBody Course course) {
        Course courseObj = courseService.registerCourse(course);
        if(courseObj!=null ) {
            return new ResponseEntity<>(courseObj, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path="/createStudent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Student> createOrSaveStudent(@RequestBody Student student) {
        Student studentObj = studentService.registerStudent(student);
        if(studentObj!=null ) {
            return new ResponseEntity<>(studentObj, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path="/getStudent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Student>> getcCourse(@RequestBody SearchStudentList search) {

        List<Student> list= studentService.getStudents(search);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}