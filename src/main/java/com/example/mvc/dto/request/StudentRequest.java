package com.example.mvc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private String name;
    private String email;
    private Long categoryId;
}
