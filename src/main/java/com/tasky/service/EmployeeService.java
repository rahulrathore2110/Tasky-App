package com.tasky.service;

import com.tasky.DTO.EmployeeDTO;
import com.tasky.exception.EmployeeException;
import com.tasky.exception.ManagerException;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO,Integer managerId) throws EmployeeException, ManagerException;


}
