package com.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springmvc.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

	public List<Project> findByProjectStatus(String status);

	public List<Project> findByProjectName(List<String> projectList);
	
	public Project findByProjectName(String projectName);
}