package com.springmvc.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.springmvc.model.IssuesList;
import com.springmvc.model.Project;
import com.springmvc.model.ProjectInfo;
import com.springmvc.model.Sprint;
import com.springmvc.repository.ProjectRepository;
import com.springmvc.repository.SprintRepository;
import com.springmvc.service.ProjectInfoService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
public class ProjectInfoServiceImpl implements ProjectInfoService{

	@Resource
    private ProjectRepository projectRepository;
	
	@Resource
    private SprintRepository sprintRepository;
	
	@Override
	public List<ProjectInfo> findIssuesCount(String date) {
		List<Project> projectDetails = projectRepository.findByProjectStatus("ACTIVE");
		Client client = Client.create();
		List<ProjectInfo> projectInfoList = new ArrayList<>();
		
	    projectDetails.stream().forEach(board-> {
	    	System.out.println(".....Project Name...."+board.getProjectName());
	    	
	    	/*client.addFilter(new HTTPBasicAuthFilter(board.getEmail(), board.getPassword()));
	    	WebResource webResource1 = client.resource(board.getUrl()+"/rest/agile/1.0/board/1/sprint");
	    	SprintsList sl = webResource1.accept(MediaType.APPLICATION_JSON_TYPE).get(SprintsList.class);
	    	*/
	    	
	    	List<Sprint> sl = sprintRepository.findAll();
	    	sl.stream().forEach(s->{
	    		LocalDate localDate = LocalDate.parse(date);
	    		LocalDate startDate= LocalDate.parse(s.getStartDate().substring(0,10));
	    		LocalDate endDate= LocalDate.parse(s.getEndDate().substring(0,10));
	    		if(localDate.isAfter(startDate) && localDate.isBefore(endDate)){
	    			System.out.println("sprint id..."+s.getJiraSprintId());
	    			client.addFilter(new HTTPBasicAuthFilter(board.getEmail(), board.getPassword()));
	    			WebResource web = client.resource(board.getUrl()+"/rest/agile/1.0/sprint/"+
	    									s.getJiraSprintId()+"/issue?maxResults=1000");
	    			IssuesList issuesList = web.accept(MediaType.APPLICATION_JSON_TYPE).get(IssuesList.class);
	    			
	    			ProjectInfo pi = new ProjectInfo();
	    			
	    			long toDoCount = issuesList.getIssues().stream().filter(p->p.getFields().
	    					getStatus().getIssueStatusName().equals("To Do")).count();
	    			long inProgressCount = issuesList.getIssues().stream().filter(p->p.getFields().
	    					getStatus().getIssueStatusName().equals("In Progress")).count();
	    			long doneCount = issuesList.getIssues().stream().filter(p->p.getFields().
	    					getStatus().getIssueStatusName().equals("Done")).count();
	    			long newCount = issuesList.getIssues().stream().filter(p->p.getFields().
	    					getStatus().getIssueStatusName().equals("New")).count();
	    			long approveCount = issuesList.getIssues().stream().filter(p->p.getFields().
	    					getStatus().getIssueStatusName().equals("Approve")).count();
	    			long reOpenCount = issuesList.getIssues().stream().filter(p->p.getFields().
	    					getStatus().getIssueStatusName().equals("ReOpened")).count();
	    			long closedCount = issuesList.getIssues().stream().filter(p->p.getFields().
	    					getStatus().getIssueStatusName().equals("Closed")).count();
	    			System.err.println(".....Date>>>>>>>"+startDate.plusDays(4));
	    			if(startDate.plusDays(4).isEqual(localDate) || startDate.plusDays(4).isAfter(localDate)){
	    				pi.setWeekOfSprint(1);
	    			}
	    			else{
	    				pi.setWeekOfSprint(2);
	    			}
	    			
	    			pi.setToDoCount(toDoCount);
	    			pi.setInProgressCount(inProgressCount);
	    			pi.setDoneCount(doneCount);
	    			pi.setNewCount(newCount);
	    			pi.setApproveCount(approveCount);
	    			pi.setReOpenCount(reOpenCount);
	    			pi.setClosedCount(closedCount);
	    			pi.setProjectName(issuesList.getIssues().get(0).getFields().getProject().getSprintProjectName());
    				pi.setProjectKey(issuesList.getIssues().get(0).getFields().getProject().getSprintProjectKey());
    				pi.setSprintId(s.getJiraSprintId());
    				pi.setSprintName(s.getSprintName());
	    			projectInfoList.add(pi);
	    		}
	    		
	    	});
	    	});
	    client.destroy();
		return projectInfoList;
	}

}
