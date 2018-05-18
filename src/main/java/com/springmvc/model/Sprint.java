package com.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sprint")
@Entity
public class Sprint {
	
	@Id
	@GeneratedValue
	private int sprintId;
	
	@ManyToOne
    @JoinColumn(name="projectId")
	private Project project;
	
	@JsonProperty("id")
	private int jiraSprintId;
	
	@JsonProperty("state")
	private String sprintState;
	
	@JsonProperty("name")
	private String sprintName;
	
	@JsonProperty("startDate")
	private String startDate;
	
	@JsonProperty("endDate")
	private String endDate;
	
	@JsonProperty("completeDate")
	private String completeDate;
}
