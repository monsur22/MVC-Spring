package com.example.mvc.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentShort {
    private Long id;
    private String name;
    private String email;
}
