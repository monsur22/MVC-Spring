package com.example.mvc.service.impl;

import com.example.mvc.model.StuCategory;
import com.example.mvc.repository.StuCategoryRepository;
import com.example.mvc.service.StuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StuCategoryServiceImpl implements StuCategoryService {

    private final StuCategoryRepository stuCategoryRepository;

    @Override
    public List<StuCategory> getStuCategory() {
        return stuCategoryRepository.findAll();
    }

    @Override
    public StuCategory storeStudentCategory(StuCategory stuCategory) {
        return stuCategoryRepository.save(stuCategory);
    }
}
