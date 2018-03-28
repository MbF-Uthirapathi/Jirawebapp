package com.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvc.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{



}
