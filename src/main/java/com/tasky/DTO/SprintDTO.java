package com.tasky.DTO;


import com.tasky.model.Manager;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Data
public class SprintDTO {
    private int id;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    private List<TaskDTO> tasks;

}
