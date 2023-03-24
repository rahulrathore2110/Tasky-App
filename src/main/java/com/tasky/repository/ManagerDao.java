package com.tasky.repository;

import com.tasky.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDao extends JpaRepository<Manager,Integer> {
    public Manager findByEmail(String email);
}
