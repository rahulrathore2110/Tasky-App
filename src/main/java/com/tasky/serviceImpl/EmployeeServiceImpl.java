package com.tasky.serviceImpl;

import com.tasky.DTO.EmployeeDTO;
import com.tasky.exception.EmployeeException;
import com.tasky.exception.ManagerException;
import com.tasky.model.Employee;
import com.tasky.model.Manager;
import com.tasky.repository.EmployeeDao;
import com.tasky.repository.ManagerDao;
import com.tasky.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ManagerDao managerDao;


    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO,Integer managerId) throws EmployeeException, ManagerException {
        Employee employee = employeeDao.findByEmail(employeeDTO.getEmail());
        if(employee != null){
            throw new EmployeeException("Employee already exist with this email : "+employeeDTO.getEmail());
        }

        Manager manager = managerDao.findById(managerId).orElseThrow(() -> new ManagerException("Manager not found " +
                "with this id : " + managerId));

        employee = modelMapper.map(employeeDTO, Employee.class);
        manager.getEmployees().add(employee);
        managerDao.save(manager);
        return modelMapper.map(employee, EmployeeDTO.class);
    }
}
