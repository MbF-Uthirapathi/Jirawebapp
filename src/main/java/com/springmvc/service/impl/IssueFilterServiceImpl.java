package com.springmvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.model.IssueFilter;
import com.springmvc.repository.ProjectRepository;
import com.springmvc.service.IssueFilterService;

@Service
public class IssueFilterServiceImpl implements IssueFilterService{

	@Resource
    private ProjectRepository projectRepository;
	
	@Override
	public Object getIssuesWithStatus(IssueFilter user) {
		
		/*List<Project> projectlist = projectRepository.findByProjectNameIn(user.getProjectName());
		Client client = Client.create();
		projectlist.forEach(p ->{
			client.addFilter(new HTTPBasicAuthFilter(p.getEmail(), p.getPassword()));
			user.getAssignee().forEach(action);
			String url = " >= " + user.getFromDate() + " " + "AND" + " " + "created" + " <= " + user.getToDate() + " " + "AND assignee ="
                    + ASSIGNEE;
            ecodedValue1 = URLEncoder.encode(url, StandardCharsets.UTF_8.name());
            WebResource web  = client.resource(URL + "/rest/api/2/search?filter&jql=" + "created" + ecodedValue1
                    + "&fields=worklog,timetracking,summary,assignee.timespent,aggregatetimeoriginalestimate,timeoriginalestimate,assignee,timespent,status,project,issuetype");
		});*/
		return null;
	}

}
