package com.tasky.controller;

import com.tasky.DTO.SprintDTO;
import com.tasky.exception.ManagerException;
import com.tasky.exception.SprintException;
import com.tasky.service.SprintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tasky/sprint")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @PostMapping("/{managerId}")
    public ResponseEntity<SprintDTO> addSprintHandler(@Valid @RequestBody SprintDTO sprintDTO, @PathVariable Integer managerId) throws SprintException, ManagerException {
        SprintDTO sprintDTO1 = sprintService.addSprint(sprintDTO, managerId);
        return new ResponseEntity<>(sprintDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<SprintDTO> updateSprintHandler(@Valid @RequestBody SprintDTO sprintDTO) throws SprintException {
        SprintDTO sprintDTO1 = sprintService.updateSprint(sprintDTO);
        return new ResponseEntity<>(sprintDTO1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{sprintID}/{managerId}")
    public ResponseEntity<String> deleteSprintByIdHandler(@PathVariable Integer sprintID, @PathVariable Integer managerId) throws SprintException, ManagerException {
        String deleteSprint = this.sprintService.deleteSprint(sprintID, managerId);
        return new ResponseEntity<>(deleteSprint, HttpStatus.OK);
    }

    @GetMapping("/{sprintID}")
    public ResponseEntity<SprintDTO> geySprintByIdHandler(@Valid @PathVariable Integer sprintID) throws SprintException {
        SprintDTO sprintDTO = sprintService.getSprintById(sprintID);
        return new ResponseEntity<>(sprintDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SprintDTO>> geyAllSprintHandler() throws SprintException {
        List<SprintDTO> sprintDTOS = sprintService.getAllSprint();
        return new ResponseEntity<>(sprintDTOS, HttpStatus.OK);
    }

}
