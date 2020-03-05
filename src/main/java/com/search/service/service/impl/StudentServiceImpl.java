package com.search.service.service.impl;

import com.search.service.beans.SearchSpec;
import com.search.service.beans.SearchStudentList;
import com.search.service.beans.Student;
import com.search.service.common.StudentSpecificationsBuilder;
import com.search.service.repository.StudentRepo;
import com.search.service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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


    public List<Student> getStudents(SearchStudentList spec) {
        StudentSpecificationsBuilder builder = new StudentSpecificationsBuilder();
        List l =spec.getSearchSpecList();
        for(int i=0; i<l.size(); i++){
            SearchSpec s = (SearchSpec) l.get(i);
            builder.with(s.getIsPredicate(),s.getKey(),s.getOps(),s.getValue(),s.getPrefix(),s.getSuffix());
        }
        Specification s1 = builder.build();
        return studentService.findAll(s1);
    }
    @Override
    public List<Student> getAllBySpecification(Specification<Student> specification) {
        // combine original specification (passed from outside) and filter-by-types specification
        Specification<Student> finalSpec = specification
                .and((root, query, cb) -> root.get("lastName").in(Arrays.asList(new String[]{"raina", "pqr"})));
        return studentService.findAll(finalSpec);
    }
}
