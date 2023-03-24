package com.tasky.DTO;

import com.tasky.model.Sprint;
import com.tasky.model.Status;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class TaskDTO {
    private int id;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate updateDate;
    private EmployeeDTO employee;
    private Status status;
}
