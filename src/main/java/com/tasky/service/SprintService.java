package com.tasky.service;

import com.tasky.DTO.SprintDTO;
import com.tasky.exception.ManagerException;
import com.tasky.exception.SprintException;
import com.tasky.model.Sprint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SprintService {

    public SprintDTO addSprint(SprintDTO sprintDTO,Integer managerId) throws SprintException, ManagerException;

    public String deleteSprint(Integer sprintId,Integer managerId) throws SprintException, ManagerException;

    public SprintDTO updateSprint(SprintDTO sprintDTO) throws SprintException;

    public List<SprintDTO> getAllSprint() throws SprintException;

    public SprintDTO getSprintById(Integer sprintId) throws SprintException;


}
