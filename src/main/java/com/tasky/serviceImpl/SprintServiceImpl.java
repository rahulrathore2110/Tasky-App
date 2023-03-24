package com.tasky.serviceImpl;

import com.tasky.DTO.SprintDTO;
import com.tasky.exception.ManagerException;
import com.tasky.exception.SprintException;
import com.tasky.model.Manager;
import com.tasky.model.Sprint;
import com.tasky.repository.ManagerDao;
import com.tasky.repository.SprintDao;
import com.tasky.service.SprintService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintDao sprintDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ManagerDao managerDao;

    @Override
    public SprintDTO addSprint(SprintDTO sprintDTO,Integer managerId) throws SprintException, ManagerException {
        Manager manager = managerDao.findById(managerId).orElseThrow(() -> new ManagerException("No manager found with this id : "+managerId));

        Sprint sprint = modelMapper.map(sprintDTO, Sprint.class);
        sprint.setStartDate(LocalDate.now());
        manager.getSprints().add(sprint);
        sprintDao.save(sprint);
        return modelMapper.map(sprint, SprintDTO.class);
    }

    @Override
    public String deleteSprint(Integer sprintId,Integer managerId) throws SprintException, ManagerException {
        Sprint sprint = sprintDao.findById(sprintId).orElseThrow(()->new SprintException("No sprint found with this " +
                "Id : "+sprintId));
        Manager manager = managerDao.findById(managerId).orElseThrow(() -> new ManagerException("Manager Not found " +
                "with this id"));
        List<Sprint> sprints = manager.getSprints();
        sprints.remove(sprint);
        sprintDao.delete(sprint);
        return "Sprint delete successfully";

    }

    @Override
    public SprintDTO updateSprint(SprintDTO sprintDTO) throws SprintException {
        Sprint sprint = sprintDao.findById(sprintDTO.getId()).orElseThrow(()->new SprintException("No sprint found with this " +
                "Id : "+ sprintDTO.getId()));

        sprint.setDescription(sprintDTO.getDescription());
        sprint.setStartDate(sprintDTO.getStartDate());
        sprint.setDueDate(sprintDTO.getDueDate());

        sprintDao.save(sprint);
        return modelMapper.map(sprint, SprintDTO.class);
    }

    @Override
    public List<SprintDTO> getAllSprint() throws SprintException {
        List<Sprint> sprints = sprintDao.findAll();
        if (sprints.isEmpty()){
            throw new SprintException("No Sprint found");
        }

        List<SprintDTO> sprintDTOS = sprints.stream().map(sprint -> modelMapper.map(sprint, SprintDTO.class)).collect(Collectors.toList());

        return sprintDTOS;
    }

    @Override
    public SprintDTO getSprintById(Integer sprintId) throws SprintException {
        Sprint sprint = sprintDao.findById(sprintId).orElseThrow(()->new SprintException("No sprint found with this " +
                "id: " + sprintId));

        return modelMapper.map(sprint, SprintDTO.class);
    }
}
