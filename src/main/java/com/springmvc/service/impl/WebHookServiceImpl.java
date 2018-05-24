package com.springmvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.model.IssueDetail;
import com.springmvc.model.Project;
import com.springmvc.model.Sprint;
import com.springmvc.model.SprintProject;
import com.springmvc.model.WebhookEventRequest;
import com.springmvc.repository.IssueRepository;
import com.springmvc.repository.ProjectRepository;
import com.springmvc.repository.SprintRepository;
import com.springmvc.service.WebHookService;

@Service
public class WebHookServiceImpl implements WebHookService {

	@Resource
	private ProjectRepository projectRepository;

	@Resource
	private SprintRepository sprintRepository;
	
	@Resource
	private IssueRepository issueRepository;
	
	

	@Override
	public String updateIssueNotification(WebhookEventRequest eventReqest) {
		System.out.println("webhook request :" +eventReqest);
		System.out.println("WebHook...................................:"+eventReqest.getWebhookEvent());
		if(eventReqest.getWebhookEvent().equals("jira:issue_created")){
		IssueDetail issueDetail = new IssueDetail();
		issueDetail.setAssignee(eventReqest.getIssue().getFields().getAssignee().getAssigneename());
		issueDetail.setJiraIssueId(eventReqest.getIssue().getIssueId());
		issueDetail.setCreatedDate(eventReqest.getIssue().getFields().getCreatedDate());
		issueDetail.setIssueStatus(eventReqest.getIssue().getFields().getStatus().getIssueStatusName());
		issueDetail.setIssueSummary(eventReqest.getIssue().getFields().getSummary());
		issueDetail.setJiraProjectName(eventReqest.getIssue().getFields().getProject().getSprintProjectName());
		issueDetail.setIssueKey(eventReqest.getIssue().getIssueKey());
		issueDetail.setIssueType(eventReqest.getIssue().getFields().getIssuetype().getIssuetypename());
		issueDetail.setTimeSpent(eventReqest.getIssue().getFields().getTimeTrack().getTimeSpent());
		issueDetail.setRemainingTime(eventReqest.getIssue().getFields().getTimeTrack().getRemainTime());
		issueDetail.setOriginalEstimationTime(eventReqest.getIssue().getFields().getTimeTrack().getOriginalTime());
		issueDetail.setCreatedDate(eventReqest.getIssue().getFields().getCreatedDate());
		Project project = projectRepository
				.findByProjectName(eventReqest.getIssue().getFields().getProject().getSprintProjectName());
		issueDetail.setProject(project);
		if(eventReqest.getIssue().getFields().getSprint() !=null){
		Sprint sprint = sprintRepository
				.findByJiraSprintId(eventReqest.getIssue().getFields().getSprint().getJiraSprintId());
		issueDetail.setSprint(sprint);
		}
		IssueDetail is = issueRepository.save(issueDetail);
		System.out.println("issue detail  *** :"  +is.getIssueId());
		}
		return "WbHook--------------------->>>>>>>>>>";
	}

}
