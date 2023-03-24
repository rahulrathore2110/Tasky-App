package com.tasky.controller;

import com.tasky.DTO.SprintDTO;
import com.tasky.DTO.TaskDTO;
import com.tasky.exception.EmployeeException;
import com.tasky.exception.SprintException;
import com.tasky.exception.TaskException;
import com.tasky.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasky/tasks")
public class TaskController {

    @Autowired
    public TaskService taskService;

    @PostMapping("/{sprintID}")
    public ResponseEntity<TaskDTO> addTaskHandler(@Valid @RequestBody TaskDTO taskDTO, @PathVariable Integer sprintID) throws SprintException {
        TaskDTO taskDTO1 = taskService.addTask(taskDTO,sprintID);
        return new ResponseEntity<>(taskDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<TaskDTO> updateTaskHandler(@Valid @RequestBody TaskDTO taskDTO) throws TaskException {
        TaskDTO taskDTO1 = taskService.updateTask(taskDTO);
        return new ResponseEntity<>(taskDTO1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{taskId}/{sprintID}")
    public ResponseEntity<String > deleteTaskHandler(@Valid @PathVariable Integer taskId,
                                                     @PathVariable Integer sprintID) throws TaskException, SprintException {
        String taskDTO1 = taskService.deleteTask(taskId,sprintID);
        return new ResponseEntity<>(taskDTO1, HttpStatus.OK);
    }

    @GetMapping("/{sprintID}")
    public ResponseEntity<TaskDTO> getTaskByIdHandler(@Valid @PathVariable Integer sprintID) throws  TaskException {
        TaskDTO taskDTO1 = taskService.getTaskById(sprintID);
        return new ResponseEntity<>(taskDTO1, HttpStatus.OK);
    }

    @PutMapping("/status/{status}/{taskId}")
    public ResponseEntity<TaskDTO> changeTaskStatusHandler(@Valid @PathVariable String  status,
                                                           @PathVariable Integer taskId) throws  TaskException {
        TaskDTO taskDTO1 = taskService.changeTaskStatus(status,taskId);
        return new ResponseEntity<>(taskDTO1, HttpStatus.ACCEPTED);
    }

    @PutMapping("/assign/{taskId}/{empId}")
    public ResponseEntity<TaskDTO> assignTaskHandler(@Valid @PathVariable Integer  taskId,
                                                           @PathVariable Integer empId) throws TaskException, EmployeeException {
        TaskDTO taskDTO1 = taskService.assignTask(taskId,empId);
        return new ResponseEntity<>(taskDTO1, HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskDTO>> getAllTaskHandler() throws  TaskException {
        List<TaskDTO> taskDTOS = taskService.getAllTask();
        return new ResponseEntity<>(taskDTOS, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<TaskDTO>> searchTaskByDescriptionHandler(@Valid @PathVariable String keyword) throws  TaskException {
        List<TaskDTO> taskDTOS = taskService.searchTaskByDescription(keyword);
        return new ResponseEntity<>(taskDTOS, HttpStatus.OK);
    }


}
