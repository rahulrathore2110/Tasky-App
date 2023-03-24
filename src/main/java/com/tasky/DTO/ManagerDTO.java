package com.tasky.DTO;


import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ManagerDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private Set<EmployeeDTO> employees;
    private List<SprintDTO> sprints;
}
