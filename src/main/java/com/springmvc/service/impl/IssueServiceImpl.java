package com.springmvc.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.model.JiraRequest;
import com.springmvc.model.JiraUser;
import com.springmvc.model.Project;
import com.springmvc.repository.JiraUserRepository;
import com.springmvc.repository.ProjectRepository;
import com.springmvc.service.IssueService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
public class IssueServiceImpl implements IssueService{

	@Resource
    private ProjectRepository projectRepository;
	
	@Resource
    private JiraUserRepository jiraUserRepository;
	
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	private static String PROJECT;
	    
	@Override
	public List<String> findIssue(JiraRequest user) throws UnsupportedEncodingException {

        String jsonResponse = null;
        WebResource webResource = null;
        ClientResponse response = null;
        String ecodedValue1=null;
        String fromDate = user.getFromDate();
        String toDate = user.getToDate();
        String ASSIGNEE =user.getAssignName();
        Client client = Client.create();

        List<String> json = new ArrayList<String>();

        List<Project> projectDetails1 = projectRepository.findAll();
        List<JiraUser> jiraUser= jiraUserRepository.findAll();
        for (Project jira1 : projectDetails1) {
            for (JiraUser listUser : jiraUser){
                
                ASSIGNEE=listUser.getAssigneeName();
                String PROJECTNAME = listUser.getProjectName();
            
            USERNAME = jira1.getEmail();
            PASSWORD = jira1.getPassword();
            URL = jira1.getUrl();
            PROJECT = jira1.getProjectName();
           
            client.addFilter(new HTTPBasicAuthFilter(USERNAME, PASSWORD));
            String url = " >= " + fromDate + " " + "AND" + " " + "created" + " <= " + toDate + " " + "AND assignee ="
                    + ASSIGNEE;
            ecodedValue1 = URLEncoder.encode(url, StandardCharsets.UTF_8.name());
            webResource = client.resource(URL + "/rest/api/2/search?filter&jql=" + "created" + ecodedValue1
                    + "&fields=worklog,timetracking,summary,assignee.timespent,aggregatetimeoriginalestimate,timeoriginalestimate,assignee,timespent,status,project,issuetype");
            try{
            	response = webResource.type("application/json").accept("application/json").get(ClientResponse.class);
            	System.err.println(response.getStatus());
            }
            catch (Exception e){
            	System.err.println(e.getMessage()+" for this "+PROJECT);
            	e.printStackTrace();
            }
            client.destroy();
            jsonResponse = response.getEntity(String.class);
            json.add(jsonResponse);

            }
        }
        return json;
    
	}

}
