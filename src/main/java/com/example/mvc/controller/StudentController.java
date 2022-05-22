package com.example.mvc.controller;

import com.example.mvc.model.Student;
import com.example.mvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String getStudent(Model model) {
        List<Student> listStudents = studentService.getStudents();
        model.addAttribute("listStudents", listStudents);
        return "index";
    }

    @GetMapping("/student/add")
    public String addStudent(Student student, Model model) {
        return "add_student";
    }

    @PostMapping("/student/add")
    public String storeStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        studentService.storeStudent(student);
        redirectAttributes.addFlashAttribute("success", "Data add Successfully");
        return "redirect:/";
    }

    @GetMapping("/students/{id}")
    public String getStudentsById(@PathVariable Long id, Model model){
        Student student = studentService.getStudentsById(id);
        model.addAttribute("student",student);
        return "edit_student";
    }

    @PostMapping ("/students/update")
    public String updateStudentsById(@ModelAttribute("student") Student student,RedirectAttributes redirectAttributes){
        studentService.updateStudentsById(student);
        redirectAttributes.addFlashAttribute("success", "Data Update Successfully");
        return "redirect:/";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudentsById(@PathVariable Long id,RedirectAttributes redirectAttributes){
        studentService.deleteStudentsById(id);
        redirectAttributes.addFlashAttribute("success", "Data Delete Successfully");
        return "redirect:/";
    }

}
