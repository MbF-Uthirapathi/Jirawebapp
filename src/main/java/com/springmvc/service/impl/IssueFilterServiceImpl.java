package com.springmvc.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.model.Issue;
import com.springmvc.model.IssueFilter;
import com.springmvc.model.IssueFilterList;
import com.springmvc.model.IssuesList;
import com.springmvc.model.Project;
import com.springmvc.repository.JiraUserRepository;
import com.springmvc.repository.ProjectRepository;
import com.springmvc.service.IssueFilterService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
public class IssueFilterServiceImpl implements IssueFilterService {

	@Resource
	private ProjectRepository projectRepository;

	@Resource
	private JiraUserRepository jiraUserRepository;

	@Override
	public List<IssueFilterList> getIssuesWithStatus(IssueFilter user) {
        
		String ASSIGNEE = String.join(",", user.getAssignee());
		Client client = Client.create();
		List<IssueFilterList> issueList = new ArrayList<IssueFilterList>();

		List<Project> projectDetails1 = projectRepository.findByProjectName(user.getProjectName());

	      projectDetails1.stream().forEach(jira1->{	
	    	
	  		String ecodedValue1 = null;
			client.addFilter(new HTTPBasicAuthFilter(jira1.getEmail(), jira1.getPassword()));
			String url = " >= " + user.getFromDate() + " " + "AND" + " " + "created" + " <= " + user.getToDate() + " " + "AND assignee in ("
					+ ASSIGNEE + ")";
			try {
				ecodedValue1 = URLEncoder.encode(url, StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			WebResource webResource = client.resource( jira1.getUrl()+ "/rest/api/2/search?jql=" + "created" + ecodedValue1
					+ "&fields=worklog,timetracking,summary,assignee.timespent,aggregatetimeoriginalestimate,timeoriginalestimate,assignee,timespent,status,project,issuetype");

			IssuesList response = webResource.type("application/json").accept("application/json").get(IssuesList.class);
			List<Issue> s=response.getIssues();			
			s.forEach(p ->{
				IssueFilterList issue = new IssueFilterList();
				issue.setProjectName(jira1.getProjectName());
				issue.setIssueId(p.getIssueId());
				issue.setAssignee(p.getFields().getAssignee().getAssigneename());
				issue.setIssueType(p.getFields().getIssuetype().getIssuetypename());
				issue.setSummary(p.getSummary());
				issue.setAssigneeEmailAdd(p.getFields().getAssignee().getAssigneeEmailAddress());
				issue.setStatusName(p.getFields().getStatus().getIssueStatusName());
				issue.setOriginalEstimate(p.getFields().getTimeTrack().getOriginalTime());
				issue.setRemainingEstimate(p.getFields().getTimeTrack().getRemainTime());
				issue.setTimeSpent(p.getFields().getTimeTrack().getTimeSpent());
				issueList.add(issue);
			});
			});		
		client.destroy();
		return issueList;
	}

}
