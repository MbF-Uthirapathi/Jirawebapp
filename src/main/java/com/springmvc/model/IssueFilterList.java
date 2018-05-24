package com.springmvc.model;

import lombok.Data;

@Data
public class IssueFilterList {
	  
    private String projectName;
    
    private String issueId;
    
    private String summary;

    private String issueType;
    
    private String assignee;
    
    private String assigneeEmailAdd;
    
    private String originalEstimate;
    
    private String remainingEstimate;

    private String timeSpent;
    
    private String statusName;

}
