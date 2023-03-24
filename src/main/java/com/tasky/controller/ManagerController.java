package com.tasky.controller;

import com.tasky.DTO.EmployeeDTO;
import com.tasky.DTO.ManagerDTO;
import com.tasky.exception.ManagerException;
import com.tasky.model.Employee;
import com.tasky.model.Manager;
import com.tasky.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasky/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/")
    public ResponseEntity<ManagerDTO> addManagerHandler(@Valid @RequestBody ManagerDTO managerDTO) throws ManagerException {
        ManagerDTO managerDTO1 = managerService.addManager(managerDTO);
        return new ResponseEntity<ManagerDTO>(managerDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/emp/{managerId}")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeUnderManagerHandler(@Valid @PathVariable Integer managerId) throws ManagerException {
        List<EmployeeDTO> managerDTO1 = managerService.getAllEmployee(managerId);
        return new ResponseEntity<List<EmployeeDTO>>(managerDTO1, HttpStatus.OK);
    }

    @GetMapping("/{managerId}")
    public ResponseEntity<ManagerDTO> getManagerByIdHandler(@Valid @PathVariable Integer managerId) throws ManagerException {
        ManagerDTO manager = managerService.getMangerById(managerId);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }
}
