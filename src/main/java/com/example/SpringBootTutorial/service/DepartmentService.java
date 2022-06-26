package com.example.SpringBootTutorial.service;

import com.example.SpringBootTutorial.entity.Department;
import com.example.SpringBootTutorial.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> getDepartmentList();

    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public List<Department> saveDepartments(List<Department> departments);

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

     Department getDepartmentByName(String departmentName);
}
