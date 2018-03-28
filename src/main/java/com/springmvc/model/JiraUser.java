package com.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

@Table(name = "jiraUser")
public class JiraUser {

    @Id
    @GeneratedValue
    private int jiraId;

    

    private String email;

    
/*      private String password;

 * private String jiraUsername;
 *   private String url;
 *   
 *    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="userId")
    private User employee;
*/    
    private String projectName;
   
    private String assigneeName;
    
   
    
}
