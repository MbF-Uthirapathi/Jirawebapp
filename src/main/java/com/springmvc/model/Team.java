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

@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue
    private int teamId;
    
    private String teamName;
    
    private String teamLoginName;
    
    private String teamLoginPwd;

}
