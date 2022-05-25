package com.example.mvc.controller;

import com.example.mvc.model.Student;
import com.example.mvc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StudentController {
    private final StudentService studentService;

    private final String studentIndexPage = "index";
    private final String studentAddPage = "add_student";
    private final String studentEditPage = "edit_student";
    private final String redirectPage = "redirect:/";


    @GetMapping(WebConstants.GET_STUDENT)
    public String getStudent(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("listStudents", students);
        return studentIndexPage;
    }

    @GetMapping(WebConstants.ADD_STUDENT)
    public String addStudent(Student student, Model model) {
        return studentAddPage;
    }

    @PostMapping(WebConstants.STORE_STUDENT)
    public String storeStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        studentService.storeStudent(student);
        redirectAttributes.addFlashAttribute("success", "Data add Successfully");
        return redirectPage;
    }

    @GetMapping(WebConstants.GET_STUDENT_BY_ID)
    public String getStudentById(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentsById(id);
        model.addAttribute("student", student);
        return studentEditPage;
    }

    @PostMapping (WebConstants.UPDATE_STUDENT)
    public String updateStudent(@ModelAttribute("student") Student student,RedirectAttributes redirectAttributes) {
        studentService.updateStudent(student);
        redirectAttributes.addFlashAttribute("success", "Data Update Successfully");
        return redirectPage;
    }

    @GetMapping(WebConstants.DELETE_STUDENT_BY_ID)
    public String deleteStudentById(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        studentService.deleteStudentById(id);
        redirectAttributes.addFlashAttribute("success", "Data Delete Successfully");
        return redirectPage;
    }

}
