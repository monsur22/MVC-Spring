package com.example.mvc.service;

import com.example.mvc.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    Student storeStudent(Student student);

    Student getStudentsById(Long id);

    Student updateStudent(Student student);

    void deleteStudentById(Long id);


}
