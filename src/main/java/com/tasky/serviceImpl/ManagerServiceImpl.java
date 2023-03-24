package com.tasky.serviceImpl;

import com.tasky.DTO.EmployeeDTO;
import com.tasky.DTO.ManagerDTO;
import com.tasky.exception.ManagerException;
import com.tasky.model.Employee;
import com.tasky.model.Manager;
import com.tasky.repository.ManagerDao;
import com.tasky.service.ManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ManagerDTO addManager(ManagerDTO managerDTO) throws ManagerException {
        Manager manager = managerDao.findByEmail(managerDTO.getEmail());
        if (manager != null){
            throw new ManagerException("manager already exist  with this" + manager.getEmail() + " plz choose " +
                    "another email ");
        }
        manager = modelMapper.map(managerDTO, Manager.class);
        managerDao.save(manager);
        return modelMapper.map(manager, ManagerDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee(Integer managerId) throws ManagerException {
        Manager manager = managerDao.findById(managerId).orElseThrow(() -> new ManagerException("No manager found " +
                "with this id : "+managerId));

        Set<Employee> employees = manager.getEmployees();

        List<EmployeeDTO> employeeDTOS = employees.stream().map(emp -> modelMapper.map(emp,EmployeeDTO.class)).collect(Collectors.toList());
        return employeeDTOS;
    }

    @Override
    public ManagerDTO getMangerById(Integer managerId) throws ManagerException {
        Manager manager = managerDao.findById(managerId).orElseThrow(()->new ManagerException("No manager fount with " +
                "this id : "+managerId));


        return modelMapper.map(manager, ManagerDTO.class);
    }
}
