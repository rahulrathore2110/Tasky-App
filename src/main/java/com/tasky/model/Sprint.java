package com.tasky.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

}
