package com.tasky.service;

import com.tasky.DTO.EmployeeDTO;
import com.tasky.DTO.ManagerDTO;
import com.tasky.exception.ManagerException;
import com.tasky.model.Manager;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface ManagerService {

    public ManagerDTO addManager(ManagerDTO managerDTO) throws ManagerException;

    public List<EmployeeDTO> getAllEmployee(Integer managerId) throws ManagerException;

    public ManagerDTO getMangerById(Integer managerId) throws ManagerException;

}
