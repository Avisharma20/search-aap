package com.search.service.controllers;

import com.search.service.beans.Student;
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
public class SearchController{

    @Autowired
    private StudentService studentService;

    @PostMapping(path="/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Student> createOrSaveStudent(@RequestBody Student student) {
        Student studentObj = studentService.registerStudent(student);
        if(studentObj!=null ) {
            return new ResponseEntity<>(studentObj, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        Optional<Student> studentObj = studentService.getByStudentId(id);
        if(!studentObj.isEmpty()) {
            return new ResponseEntity<>(studentObj.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Data not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/students", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Student>> getStudentAll() {
        List<Student> studentObj = studentService.getAllStudents();
        if(!studentObj.isEmpty()) {
            return new ResponseEntity<>(studentObj, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

