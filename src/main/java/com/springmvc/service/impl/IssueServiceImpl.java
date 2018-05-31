package com.springmvc.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springmvc.model.Issue;
import com.springmvc.model.IssueDetail;
import com.springmvc.model.IssuesList;
import com.springmvc.model.JiraRequest;
import com.springmvc.model.JiraUser;
import com.springmvc.model.Project;
import com.springmvc.model.Sprint;
import com.springmvc.repository.IssueRepository;
import com.springmvc.repository.JiraUserRepository;
import com.springmvc.repository.ProjectRepository;
import com.springmvc.repository.SprintRepository;
import com.springmvc.service.IssueService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
public class IssueServiceImpl implements IssueService {
	
	private static final Logger log = LoggerFactory.getLogger(IssueServiceImpl.class);

	@Resource
	private ProjectRepository projectRepository;

	@Resource
	private JiraUserRepository jiraUserRepository;

	@Resource
	private SprintRepository sprintRepository;

	@Resource
	private IssueRepository issueRepository;

	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	private static String PROJECT;

	@Override
	public List<String> findIssue(JiraRequest user) throws UnsupportedEncodingException {

		String jsonResponse = null;
		WebResource webResource = null;
		ClientResponse response = null;
		String ecodedValue1 = null;
		String fromDate = user.getFromDate();
		String toDate = user.getToDate();
		String ASSIGNEE = user.getAssignName();
		Client client = Client.create();

		List<String> json = new ArrayList<String>();

		List<Project> projectDetails1 = projectRepository.findAll();
		List<JiraUser> jiraUser = jiraUserRepository.findAll();
		for (Project jira1 : projectDetails1) {
			for (JiraUser listUser : jiraUser) {

				ASSIGNEE = listUser.getAssigneeName();

				USERNAME = jira1.getEmail();
				PASSWORD = jira1.getPassword();
				URL = jira1.getUrl();
				PROJECT = jira1.getProjectName();

				client.addFilter(new HTTPBasicAuthFilter(USERNAME, PASSWORD));
				String url = " >= " + fromDate + " " + "AND" + " " + "created" + " <= " + toDate + " "
						+ "AND assignee =" + ASSIGNEE;
				ecodedValue1 = URLEncoder.encode(url, StandardCharsets.UTF_8.name());
				webResource = client.resource(URL + "/rest/api/2/search?filter&jql=" + "created" + ecodedValue1
						+ "&fields=worklog,timetracking,summary,assignee.timespent,aggregatetimeoriginalestimate,timeoriginalestimate,assignee,timespent,status,project,issuetype");
				try {
					response = webResource.type("application/json").accept("application/json")
							.get(ClientResponse.class);
					log.info("---"+response.getStatus());
				} catch (Exception e) {
					log.info(e.getMessage() + " for this " + PROJECT);
					e.printStackTrace();
				}
				client.destroy();
				jsonResponse = response.getEntity(String.class);
				json.add(jsonResponse);

			}
		}
		return json;

	}

	@Override
	//@Scheduled(cron = "0 1 1 * * ?")
	public void addIssues() throws UnsupportedEncodingException {
		LocalDate currentdate = LocalDate.now();
		Client client = Client.create();
		List<IssueDetail> issueDetails = new ArrayList<IssueDetail>();
		List<Project> projectDetails1 = projectRepository.findAll();
		projectDetails1.stream().forEach(jira1 -> {
			WebResource webResource=null;
			String url = null;
			if (!jira1.isPullIssues()) {
				log.info("pulled issues");
				url = " <= " + currentdate;
				jira1.setPullIssues(true);
				projectRepository.save(jira1);
			} else {
				log.info("today issues updated");
				url = "=" + currentdate;
			}
			client.addFilter(new HTTPBasicAuthFilter(jira1.getEmail(), jira1.getPassword()));

			String ecodedValue1 = null;
			try {
				ecodedValue1 = URLEncoder.encode(url, StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			webResource = client.resource(jira1.getUrl() + "/rest/api/2/search?filter&jql=" + "created"
					+ ecodedValue1);
			
			IssuesList response = webResource.type("application/json").accept("application/json").get(IssuesList.class);

			log.info("List of issues :" +response.getTotal());
	
			for(int i=0; i<=response.getTotal();i+=50){
			
				webResource = client.resource(jira1.getUrl() + "/rest/api/2/search?filter&jql=" + "created"
						+ ecodedValue1+"&startAt="+i +"&maxResults=50");
				
				IssuesList res = webResource.type("application/json").accept("application/json").get(IssuesList.class);
 
				log.info("jira url for get the list of issues:" +webResource.toString());
				List<Issue> s = res.getIssues();
				log.info("Issue count :"+res.getIssues().stream().count());
			
				s.forEach(p -> {
					IssueDetail issue = new IssueDetail();
					if(p.getFields().getAssignee() !=null){
						issue.setAssignee(p.getFields().getAssignee().getAssigneename());
					}
					if(p.getFields().getIssuetype()!=null){
						issue.setIssueType(p.getFields().getIssuetype().getIssuetypename());
					}
					issue.setCreatedDate(p.getFields().getCreatedDate());
					issue.setIssueKey(p.getIssueKey());
					issue.setIssueStatus(p.getFields().getStatus().getIssueStatusName());
					issue.setIssueSummary(p.getSummary());
					
					issue.setJiraIssueId(p.getIssueId());
					issue.setJiraProjectName(jira1.getProjectName());
					if(p.getFields().getSprint()!=null){
						issue.setJiraSprintId(p.getFields().getSprint().getSprintId());
						issue.setJiraSprintName(p.getFields().getSprint().getSprintName());
						Sprint sprint = sprintRepository.findByJiraSprintId(p.getFields().getSprint().getSprintId());
						issue.setSprint(sprint);
					}
					if(p.getFields().getTimeTrack()!=null){
						issue.setOriginalEstimationTime(p.getFields().getTimeTrack().getOriginalTime());
						issue.setRemainingTime(p.getFields().getTimeTrack().getRemainTime());
						issue.setTimeSpent(p.getFields().getTimeTrack().getTimeSpent());
					}
					issue.setProject(jira1);
					issueDetails.add(issue);
				});
			}
			issueRepository.save(issueDetails);
		});
	}
	
	//@Scheduled(fixedDelay =5000)
	public void scheduleTest(){
		log.info("schedule for every 5 seconds");
	}

}
