package com.search.service.service;

import com.search.service.beans.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
   public List<Student> getAllStudents();

   public Optional<Student> getByStudentId(Long studentCd);

   public Student registerStudent(Student student);
}
