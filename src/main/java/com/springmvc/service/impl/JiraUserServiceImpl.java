package com.springmvc.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.springmvc.model.JiraRequest;
import com.springmvc.model.JiraUser;
import com.springmvc.model.User;
import com.springmvc.repository.JiraUserRepository;
import com.springmvc.repository.UserRepository;
import com.springmvc.service.JiraUserService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
@Transactional

public class JiraUserServiceImpl implements JiraUserService {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String ASSIGNEE;

    @Resource
    private JiraUserRepository jiraUserRepository;

    @Resource
    private UserRepository userRepository;

    @Override
    public JiraUser findById(int jiraId) {
        return jiraUserRepository.findOne(jiraId);
    }

    @Override
    public JiraUser delete(int jiraId) {
        JiraUser deletedUser = jiraUserRepository.findOne(jiraId);
        jiraUserRepository.delete(deletedUser);
        System.err.println(deletedUser+"delete service......"+jiraId);
        return deletedUser;
    }
    /*  @Override
   @Transactional
    public List<User> findAll() {

        return userRepository.findAll();
    }*/
   /* @Override
    public List<JiraUser> findAll(Object id) {
        List<User> userList = userRepository.findByTeamDetailsTeamId(id);
       List<JiraUser> finalJiraUser = new ArrayList<JiraUser>();

        List<JiraUser> JiraUser = null;
        for (User emp1 : userList) {
            List<JiraUser> jiraUser = jiraUserRepository.findByEmployeeUserId(emp1.getUserId());
            for (JiraUser jiraId : jiraUser) {

                JiraUser = jiraUserRepository.findByJiraId(jiraId.getJiraId());
                finalJiraUser.addAll(JiraUser);

            }

        }

        return finalJiraUser;
    }*/
    public List<JiraUser> findAll() {

        return jiraUserRepository.findAll();
    }
    public JiraUser create(JiraUser user) {
        JiraUser createdUser = user;
        return jiraUserRepository.save(createdUser);
    }

    @Override
    public JiraUser update(JiraUser user) {
        return jiraUserRepository.save(user);
    }

    @Override
    public List<String> findIssue(JiraRequest user, HttpSession session)
            throws UnsupportedEncodingException {
                return null;

       /* session.getAttribute("user");
        Object id = session.getAttribute("user");

        List<User> userList = userRepository.findByTeamDetailsTeamId(id);
        String jsonResponse = null;
        WebResource webResource = null;
        ClientResponse response = null;
        String fromDate = user.getFromDate();
        String toDate = user.getToDate();
        Client client = Client.create();

        List<String> json = new ArrayList<String>();
        for (User emp1 : userList) {

            List<JiraUser> projectDetails1 = jiraUserRepository.findByEmployeeUserId(emp1.getUserId());
            for (JiraUser jira1 : projectDetails1) {
                USERNAME = jira1.getEmail();
                /*PASSWORD = jira1.getPassword();
                URL = jira1.getUrl();
                ASSIGNEE = jira1.getAssigneeName();

                client.addFilter(new HTTPBasicAuthFilter(USERNAME, PASSWORD));

            }
            String url = " >= " + fromDate + " " + "AND" + " " + "created" + " <= " + toDate + " " + "AND assignee ="
                    + ASSIGNEE;
            String ecodedValue1 = URLEncoder.encode(url, StandardCharsets.UTF_8.name());

            webResource = client.resource(URL + "rest/api/2/search?filter&jql=" + "created" + ecodedValue1
                    + "&fields=worklog,timetracking,summary,assignee.timespent,aggregatetimeoriginalestimate,timeoriginalestimate,assignee,timespent,status");
          
            System.out.println("webResource"+webResource);
            response = webResource.type("application/json").accept("application/json").get(ClientResponse.class);
            client.destroy();
            jsonResponse = response.getEntity(String.class);
            json.add(jsonResponse);

        }

        return json;*/
    }

}