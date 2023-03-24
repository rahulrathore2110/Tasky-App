package com.tasky.controller;

import com.tasky.DTO.EmployeeDTO;
import com.tasky.DTO.ManagerDTO;
import com.tasky.exception.EmployeeException;
import com.tasky.exception.ManagerException;
import com.tasky.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasky/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/{managerId}")
    public ResponseEntity<EmployeeDTO> addEmployeeHandler(@Valid @RequestBody EmployeeDTO employeeDTO,@PathVariable Integer managerId) throws ManagerException,
            EmployeeException {
        EmployeeDTO employeeDTO1 = employeeService.addEmployee(employeeDTO,managerId);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.CREATED);
    }
}
