package com.springmvc.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.springmvc.model.JiraRequest;
import com.springmvc.model.Project;

public interface ProjectService{

    Project create(Project user);

    List<Project> findAll();


    Project update(Project proUser);


    Project findById(int projectId);

    Project delete(int projectId);

    List<String> findIssue(JiraRequest user) throws UnsupportedEncodingException;

}
