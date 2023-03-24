package com.tasky.DTO;


import com.tasky.model.Manager;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private int id;
    private String name;
    private String email;
    private String role;

}
