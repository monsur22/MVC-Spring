package com.example.mvc.service.impl;

import com.example.mvc.model.Student;
import com.example.mvc.repository.StudentRepository;
import com.example.mvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List < Student > getStudents(){
        return studentRepository.findAll();
    }

    @Override
    public Student storeStudent(Student student){
        return studentRepository.save(student);

    }

    @Override
    public Student getStudentsById(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student updateStudentsById(Student student){
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentsById(Long id){
        studentRepository.deleteById(id);
    }

}
