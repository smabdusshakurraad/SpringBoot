package com.example.SpringBootTutorial.controller;

import com.example.SpringBootTutorial.entity.Department;
import com.example.SpringBootTutorial.error.DepartmentNotFoundException;
import com.example.SpringBootTutorial.service.DepartmentService;
import com.example.SpringBootTutorial.service.DepartmentServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private  DepartmentService departmentservice;

    private final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentController.class);
    @PostMapping("/department")
    public Department saveDepartment(@Valid @RequestBody Department department){
//        DepartmentService service = new DepartmentServiceImplementation(); // Creating objects without using autowire.
        LOGGER.info("Inside saveDepartment of DepartmentController");
        return departmentservice.saveDepartment(department);
    }

    @PostMapping("/departments")
    public List<Department> saveDepartments(@RequestBody List<Department> departments){
        return departmentservice.saveDepartments(departments);
    }

    @GetMapping("/departments")
    public List<Department> getDepartmentList(){

        LOGGER.info("Inside getDepartmentList of DepartmentController");
        return departmentservice.getDepartmentList();
    }

    @GetMapping("/department/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
            return departmentservice.getDepartmentById(departmentId);
    }

    @DeleteMapping("/department/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentservice.deleteDepartmentById(departmentId);
        return "Department deleted Successfully!!";
    }

    @PutMapping("/department/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return departmentservice.updateDepartment(departmentId,department);
    }

    @GetMapping("/department/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String departmentName){
        return departmentservice.getDepartmentByName(departmentName);
    }
}
