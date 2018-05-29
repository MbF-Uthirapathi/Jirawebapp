package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.model.IssuesList;
import com.springmvc.model.IssueFilter;
import com.springmvc.model.Project;
import com.springmvc.repository.IssueRepository;
import com.springmvc.repository.ProjectRepository;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
public class AddingIssueimpl {

	
	@Resource
	IssueRepository issueRepository;
	
	public void addIssues(){

		
		Client client = Client.create();

		
		Project pro=new Project();

	     // projectDetails1.stream().forEach(jira1->{	
			client.addFilter(new HTTPBasicAuthFilter(pro.getEmail(), pro.getPassword()));
			WebResource webResource = client.resource( pro.getUrl()+ "/rest/api/2/search?maxResults=1000");

			IssuesList response = webResource.type("application/json").accept("application/json").get(IssuesList.class);
			    
			issueRepository.save(response);
			
	//});
	}
}
