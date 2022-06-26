package com.example.SpringBootTutorial.controller;

import com.example.SpringBootTutorial.entity.Department;
import com.example.SpringBootTutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {

        department = Department.builder()
                .departmentName("IT")
                .departmentAddress("Dhaka")
                .departmentCode("IT-06")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputdepartment = Department.builder()
                .departmentName("IT")
                .departmentAddress("IT-06")
                .departmentAddress("Dhaka")
                .build();

        Mockito.when(departmentService.saveDepartment(inputdepartment)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"departmentName\":\"IT\",\n" +
                        "        \"departmentAddress\": \"Dhaka\",\n" +
                        "        \"departmentCode\": \"IT-06\"\n" +
                        " }"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentById(1L))
                .thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.get("/department/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}