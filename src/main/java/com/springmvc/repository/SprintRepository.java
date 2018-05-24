package com.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springmvc.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer>{

	@Query(value = "SELECT COUNT(sprintId) FROM Sprint")
	public int findCountOfRows();
	
	public Sprint findByJiraSprintId(int jiraSprintId);
}
