package com.springmvc.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueFilter {

	private String fromDate;

    private String toDate;
    
    private List<String> projectName;
    
    private List<String> assignee;
   
}
