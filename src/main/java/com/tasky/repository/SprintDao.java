package com.tasky.repository;

import com.tasky.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintDao extends JpaRepository<Sprint,Integer> {


}
