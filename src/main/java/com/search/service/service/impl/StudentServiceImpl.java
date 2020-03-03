package com.search.service.service.impl;

import com.search.service.beans.Student;
import com.search.service.repository.StudentRepo;
import com.search.service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo studentService;

    @Override
    public List<Student> getAllStudents() {
        return (List<Student>) studentService.findAll();
    }

    @Override
    public Optional<Student> getByStudentId(Long studentCd) {
        return studentService.findById(studentCd);
    }

    @Override
    public Student registerStudent(Student student) {
        return studentService.save(student);
    }

    @Override
    public List<Student> getStudent(Specification spec) {
        return studentService.findAll(spec);
    }
}
