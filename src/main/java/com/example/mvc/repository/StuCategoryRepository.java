package com.example.mvc.repository;

import com.example.mvc.model.StuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StuCategoryRepository extends JpaRepository<StuCategory , Long> {
}
