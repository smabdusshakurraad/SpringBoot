package com.example.SpringBootTutorial.service;

import com.example.SpringBootTutorial.entity.Department;
import com.example.SpringBootTutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
//Testing for service layer
@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach // we run before each and every test cases
    void setUp() {
        Department department //mocking department repository
                = Department.builder()
                .departmentId(1L)
                .departmentName("CSE")
                .departmentAddress("Dhaka")
                .departmentCode("CSE-06")
                .build();

        Mockito.when(departmentRepository.findByDepartmentName("CSE")).thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on Valid Department Name")
//    @Disabled
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
            String departmentName = "CSE";
        Department found
                = departmentService.getDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }
}