package com.tasky.repository;

import com.tasky.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task,Integer> {
    public List<Task> findByDescriptionContaining(String desc);
}
