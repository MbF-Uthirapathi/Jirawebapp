package com.springmvc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.model.JiraProjectUsers;
import com.springmvc.model.Project;
import com.springmvc.repository.JiraUserRepository;
import com.springmvc.repository.ProjectRepository;
import com.springmvc.service.ProjectService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectRepository projectRepository;
    @Resource
    private JiraUserRepository jiraUserRepository;
    
    @Override
    public Project create(Project user) {
        Project createdUser = user;
        return projectRepository.save(createdUser);
    }

    @Override
    @Transactional
    public List<Project> findAll() {
    	System.err.println("find method...");
        return projectRepository.findAll();
    }

    @Override
    public Project findById(int projectId) {
        return projectRepository.findOne(projectId);
    }

    @Override
    public Project delete(int projectId) {
        Project deletedUser = projectRepository.findOne(projectId);
        projectRepository.delete(deletedUser);
        return deletedUser;
    }

    @Override
    public Project update(Project proUser) {
        return projectRepository.save(proUser);
    }

	@Override
	public List<String> getProjectNames() {
		List<Project> pr = projectRepository.findByProjectStatus("ACTIVE");
		List<String> projects = new ArrayList<>();
		pr.forEach(p->{
			projects.add(p.getJiraProjectName());
		});
		return projects;
	}

	@Override
	public Set<String> getJiraUsers(List<String> projectList) {
		Set<String> userNames = new HashSet<>();
		List<Project> projects = projectRepository.findByProjectNameIn(projectList);
		Client client = Client.create();
		projects.forEach(p->{
			client.addFilter(new HTTPBasicAuthFilter(p.getEmail(), p.getPassword()));
			WebResource web = client.resource(p.getUrl()+"/rest/api/latest/user/search?username=%25");
			JiraProjectUsers[] response = web.accept(MediaType.APPLICATION_JSON_TYPE).get(JiraProjectUsers[].class);
			List<JiraProjectUsers> list = Arrays.asList(response);
			userNames.addAll(list.stream().map(JiraProjectUsers :: getName).collect(Collectors.toList()));
		});
		client.destroy();
		return userNames;
	}
}
