package com.springmvc.service;

import java.util.List;
import java.util.Set;

import com.springmvc.model.Project;

public interface ProjectService{

    Project create(Project user);

    List<Project> findAll();

    Project update(Project proUser);

    Project findById(int projectId);

    Project delete(int projectId);

	List<String> getProjectNames();

	Set<String> getJiraUsers(List<String> projectList);
}
