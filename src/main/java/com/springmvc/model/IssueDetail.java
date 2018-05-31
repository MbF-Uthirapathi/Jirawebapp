package com.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name = "issue")
@Entity
@ToString
public class IssueDetail {
	@Id
	@GeneratedValue
	private int issueId;
	
	private String jiraIssueId;
	
	private String jiraProjectName;
	
	private String issueType;
	
	private String timeSpent;
	
	private String remainingTime;
	
	private String originalEstimationTime;
	
	private String createdDate;
	
	private String assignee;
	
	private String issueStatus;
	
	private String issueSummary;
	
	private int jiraSprintId;
	
	private String jiraSprintName;
	
	private String issueKey;
	
	@ManyToOne
    @JoinColumn(name="sprintId")
	private Sprint sprint;
	
	@ManyToOne
    @JoinColumn(name="projectId")
	private Project project;
	

}
