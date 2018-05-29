package com.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue
    private int projectId;
    
    private String projectName;
    
    private String url;

    private String email;

    private String password;
    
    private String jiraProjectName;
    
    private String projectStatus;
    
    private boolean pullIssues; 
   
}
