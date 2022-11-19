package com.example.demo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

	// Optional<Task> findByAssignedto(String name);
	 List<Task> findByAssignedto(String assignedto);
   	 List<Task> findByTasktitle(String tasktitle);
	


}
