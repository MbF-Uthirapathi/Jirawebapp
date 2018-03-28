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

@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int userId;

    private String firstname;

    private String lastname;

    private String designation;

    @ManyToOne
    @JoinColumn(name="teamId")
    private Team teamDetails;
    

}
