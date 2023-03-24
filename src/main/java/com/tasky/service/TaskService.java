package com.tasky.service;

import com.tasky.DTO.TaskDTO;
import com.tasky.exception.EmployeeException;
import com.tasky.exception.SprintException;
import com.tasky.exception.TaskException;
import com.tasky.model.Status;
import com.tasky.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    public TaskDTO addTask(TaskDTO taskDTO,Integer sprintId) throws SprintException;

    public TaskDTO updateTask(TaskDTO taskDTO) throws TaskException;

    public String deleteTask(Integer taskId,Integer sprintId) throws SprintException, TaskException;

    public TaskDTO getTaskById(Integer taskId) throws TaskException;

    public List<TaskDTO> getAllTask() throws TaskException;

    public TaskDTO changeTaskStatus(String status,Integer taskId) throws TaskException;

    public TaskDTO assignTask(Integer taskId,Integer empId) throws TaskException, EmployeeException;

    public List<TaskDTO> searchTaskByDescription(String keyword);
}
