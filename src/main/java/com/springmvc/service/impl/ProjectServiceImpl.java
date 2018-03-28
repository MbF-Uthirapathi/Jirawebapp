package com.springmvc.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.model.JiraRequest;
import com.springmvc.model.JiraUser;
import com.springmvc.model.Project;
import com.springmvc.repository.JiraUserRepository;
import com.springmvc.repository.ProjectRepository;
import com.springmvc.service.ProjectService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectRepository projectRepository;
    @Resource
    private JiraUserRepository jiraUserRepository;

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
   

    @Override

    public Project create(Project user) {
        Project createdUser = user;
        return projectRepository.save(createdUser);
    }

    @Override
    @Transactional
    public List<Project> findAll() {

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
          /*  for (JiraUser listUser : jiraUser){
                
                ASSIGNEE=listUser.getAssigneeName();
                PROJECTNAME = listUser.getProjectName();
                System.err.println(PROJECTNAME+ASSIGNEE);*/
            
            USERNAME = jira1.getEmail();
            PASSWORD = jira1.getPassword();
            URL = jira1.getUrl();
           
            client.addFilter(new HTTPBasicAuthFilter(USERNAME, PASSWORD));
            String url = " >= " + fromDate + " " + "AND" + " " + "created" + " <= " + toDate + " " + "AND assignee ="
                    + ASSIGNEE;
            ecodedValue1 = URLEncoder.encode(url, StandardCharsets.UTF_8.name());
            webResource = client.resource(URL + "rest/api/2/search?filter&jql=" + "created" + ecodedValue1
                    + "&fields=worklog,timetracking,summary,assignee.timespent,aggregatetimeoriginalestimate,timeoriginalestimate,assignee,timespent,status,project,issuetype");
            response = webResource.type("application/json").accept("application/json").get(ClientResponse.class);
            System.err.println(response);
            client.destroy();
            jsonResponse = response.getEntity(String.class);
            json.add(jsonResponse);

          /*  }*/
        }


        
        /*
         * }
         */
        return json;
    }
}
