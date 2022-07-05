package com.example.mvc.controller;

public class WebConstants {
    public static final String GET_STUDENT = "/";
    public static final String ADD_STUDENT = "/student/add";
    public static final String STORE_STUDENT = "/student/add";
    public static final String GET_STUDENT_BY_ID = "/students/{id}";
    public static final String UPDATE_STUDENT = "/students/update";
    public static final String DELETE_STUDENT_BY_ID = "/students/delete/{id}";
    public static final String SING_IN = "/singin";
    public static final String SING_UP = "/singup";
    public static final String USER_DATA_STORE = "/user/store";
    public static final String USER_CONFIRMATION = "/user/confirmation/{token}";
    public static final String USER_LOGIN = "/user/login";
    public static final String USER_LOGOUT = "/user/logout";

    // Student category
    public static final String ADD_STUDENT_CATEGORY = "/student/category/add";
    public static final String STORE_STUDENT_CATEGORY = "/student/category/add";
    public static final String INDEX_STUDENT_CATEGORY = "/student/category/list";

}
