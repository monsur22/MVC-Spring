package com.example.mvc.service;

import com.example.mvc.model.StuCategory;

import java.util.List;

public interface StuCategoryService {
    List<StuCategory> getStuCategory();

    StuCategory storeStudentCategory(StuCategory stuCategory);
}
