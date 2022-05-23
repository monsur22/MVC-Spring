package com.example.mvc.service.impl;

import com.example.mvc.model.Student;
import com.example.mvc.repository.StudentRepository;
import com.example.mvc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student storeStudent(Student student) {
        return studentRepository.save(student);

    }

    @Override
    public Student getStudentsById(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new NullPointerException("Student not found");
        }
        return student.get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }

}
