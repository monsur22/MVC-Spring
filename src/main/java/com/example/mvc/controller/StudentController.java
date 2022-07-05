package com.example.mvc.controller;

import com.example.mvc.dto.request.StudentRequest;
import com.example.mvc.dto.response.StudentShort;
import com.example.mvc.exception.NotFoundException;
import com.example.mvc.model.StuCategory;
import com.example.mvc.model.Student;
import com.example.mvc.repository.StuCategoryRepository;
import com.example.mvc.service.StuCategoryService;
import com.example.mvc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StudentController {
    private final StudentService studentService;
    private final StuCategoryService stuCategoryService;
    private final StuCategoryRepository stuCategoryRepository;

    private final String studentIndexPage = "index";
    private final String studentAddPage = "add_student";
    private final String studentEditPage = "edit_student";
    private final String redirectPage = "redirect:/";
    private final String addStdCategoryPage = "add_std_category";
    private final String StdCategoryListPage = "category_index";


    @GetMapping(WebConstants.GET_STUDENT)
    public String getStudent(Model model) {
        List<StudentShort> studentShots = studentService.getStudents().stream()
                .filter(student -> student.getEmail() != null)
                .map(student -> StudentShort.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .email(student.getEmail())
                        .build())
                .collect(Collectors.toList());
        model.addAttribute("listStudents", studentShots);
        return studentIndexPage;
    }

    @GetMapping(WebConstants.ADD_STUDENT)
    public String addStudent(Student student, Model model) {
        List<StuCategory> stuCategories = stuCategoryService.getStuCategory();
        model.addAttribute("stuCategories", stuCategories);
        return studentAddPage;
    }

    @PostMapping(WebConstants.STORE_STUDENT)
    public String storeStudent(@ModelAttribute StudentRequest studentRequest, RedirectAttributes redirectAttributes) {
        StuCategory stuCategory = stuCategoryRepository.findById(studentRequest.getCategoryId()).orElseThrow(NotFoundException::new);
        Student student = Student.builder()
                .name(studentRequest.getName())
                .email(studentRequest.getEmail())
                .stuCategory(stuCategory)
                .build();

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

    @PostMapping(WebConstants.UPDATE_STUDENT)
    public String updateStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        studentService.updateStudent(student);
        redirectAttributes.addFlashAttribute("success", "Data Update Successfully");
        return redirectPage;
    }

    @GetMapping(WebConstants.DELETE_STUDENT_BY_ID)
    public String deleteStudentById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudentById(id);
        redirectAttributes.addFlashAttribute("success", "Data Delete Successfully");
        return redirectPage;
    }

    @GetMapping(WebConstants.ADD_STUDENT_CATEGORY)
    public String addStudentCatPage(StuCategory stuCategory, Model model) {
        return addStdCategoryPage;
    }

    @PostMapping(WebConstants.STORE_STUDENT_CATEGORY)
    public String storeStdCategory(@ModelAttribute("stuCategory") StuCategory stuCategory, RedirectAttributes redirectAttributes) {
        stuCategoryService.storeStudentCategory(stuCategory);
        redirectAttributes.addFlashAttribute("success", "Data add Successfully");
        return StdCategoryListPage;
    }

    @GetMapping(WebConstants.INDEX_STUDENT_CATEGORY)
    public String getStudentCategory(Model model) {
        List<StuCategory> stuCategories = stuCategoryService.getStuCategory();
        System.out.println(stuCategories);
        model.addAttribute("listStudentsCategory", stuCategories);
        return StdCategoryListPage;
    }

}
