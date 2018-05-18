package com.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvc.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

	public List<Project> findByProjectStatus(String status);

	public List<Project> findByProjectNameIn(List<String> projectList);
}