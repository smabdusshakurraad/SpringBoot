package com.example.SpringBootTutorial.service;

import com.example.SpringBootTutorial.entity.Department;
import com.example.SpringBootTutorial.error.DepartmentNotFoundException;
import com.example.SpringBootTutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImplementation implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepo;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public List<Department> getDepartmentList() {
        return departmentRepo.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department
                = departmentRepo.findById(departmentId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
        }

        return department.get();
    }

    @Override
    public List<Department> saveDepartments(List<Department> departments) {
        return departmentRepo.saveAll(departments);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
         departmentRepo.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {

        Department depDb = departmentRepo.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName())){
            depDb.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDb.setDepartmentCode(department.getDepartmentCode());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDb.setDepartmentAddress(department.getDepartmentAddress());
        }

        return departmentRepo.save(depDb);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentRepo.findByDepartmentName(departmentName);
    }
}
