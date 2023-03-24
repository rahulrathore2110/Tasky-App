package com.tasky.serviceImpl;

import com.tasky.DTO.TaskDTO;
import com.tasky.exception.EmployeeException;
import com.tasky.exception.SprintException;
import com.tasky.exception.TaskException;
import com.tasky.model.Employee;
import com.tasky.model.Sprint;
import com.tasky.model.Status;
import com.tasky.model.Task;
import com.tasky.repository.EmployeeDao;
import com.tasky.repository.ManagerDao;
import com.tasky.repository.SprintDao;
import com.tasky.repository.TaskDao;
import com.tasky.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private SprintDao sprintDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public TaskDTO addTask(TaskDTO taskDTO,Integer sprintId) throws SprintException {
        Sprint sprint = sprintDao.findById(sprintId).orElseThrow(()->new SprintException("No sprint found with this " +
                "Id : "+sprintId));
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setStartDate(LocalDate.now());
        task.setUpdateDate(LocalDate.now());
        task.setStatus(Status.TODO);
        sprint.getTasks().add(task);
        taskDao.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) throws TaskException {
        Task task = taskDao.findById(taskDTO.getId()).orElseThrow(()->new TaskException("task not found with this id " +
                ": "+taskDTO.getId()));
        task = modelMapper.map(taskDTO, Task.class);
        taskDao.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public String deleteTask(Integer taskId,Integer sprintId) throws SprintException, TaskException {
        Sprint sprint = sprintDao.findById(sprintId).orElseThrow(()->new SprintException("No sprint found with this " +
                "Id : "+sprintId));
        List<Task> tasks = sprint.getTasks();
        Task task = taskDao.findById(taskId).orElseThrow(()->new TaskException("task not found with this id " +
                ": "+taskId));
        tasks.remove(task);
        taskDao.delete(task);
        return "Task delete successfully";
    }

    @Override
    public TaskDTO getTaskById(Integer taskId) throws TaskException {
        Task task = taskDao.findById(taskId).orElseThrow(()->new TaskException("task not found with this id " +
                ": "+taskId));
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> getAllTask() throws TaskException {
        List<Task> tasks = taskDao.findAll();
        if (tasks.isEmpty()){
            throw new TaskException("No task found");
        }
        List<TaskDTO> taskDTOS =
                tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).collect(Collectors.toList());
        return taskDTOS;
    }

    @Override
    public TaskDTO changeTaskStatus(String status,Integer taskId) throws TaskException {
        Task task = taskDao.findById(taskId).orElseThrow(()->new TaskException("task not found with this id " +
                ": "+taskId));
        status = status.toUpperCase();
        task.setStatus(Status.valueOf(status));
        taskDao.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public TaskDTO assignTask(Integer taskId,Integer empId) throws TaskException, EmployeeException {
        Task task = taskDao.findById(taskId).orElseThrow(()->new TaskException("task not found with this id " +
                ": "+taskId));
        Employee employee = employeeDao.findById(empId).orElseThrow(()->new EmployeeException("No employee found with" +
                " this id : "+empId));
        task.setEmployee(employee);
        taskDao.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> searchTaskByDescription(String keyword) {
        List<Task> tasks = taskDao.findByDescriptionContaining(keyword);
        List<TaskDTO> taskDTOS = tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).collect(Collectors.toList());
        return taskDTOS;
    }
}
