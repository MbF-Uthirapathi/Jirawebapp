package com.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.springmvc.model.Project;
import com.springmvc.model.Sprint;
import com.springmvc.model.SprintsList;
import com.springmvc.repository.ProjectRepository;
import com.springmvc.repository.SprintRepository;
import com.springmvc.service.SprintService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@Service
public class SprintServiceImpl implements SprintService{

	@Resource
    private ProjectRepository projectRepository;
	
	@Resource
    private SprintRepository sprintRepository;
	
	@Override
	public List<Sprint> createSprints(int projectId) {
		
		Project project = projectRepository.findOne(projectId);
		int sprintCount = sprintRepository.findCountOfRows();
		System.err.println("....count......"+sprintCount);
		List<Sprint> dblist = new ArrayList<>();
		List<Sprint> reult = new ArrayList<>();
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(project.getEmail(), project.getPassword()));
		WebResource webResource1 = client.resource(project.getUrl()+"/rest/agile/1.0/board/1/sprint");
		SprintsList sl = webResource1.accept(MediaType.APPLICATION_JSON_TYPE).get(SprintsList.class);
		if(sprintCount ==0 ){
			sl.getSprintValues().forEach(list ->{
				
				Sprint sprint = new Sprint();
				sprint.setProject(project);
				sprint.setJiraSprintId(list.getJiraSprintId());
				sprint.setCompleteDate(list.getCompleteDate());
				sprint.setEndDate(list.getEndDate());
				sprint.setSprintName(list.getSprintName());
				sprint.setSprintState(list.getSprintState());
				sprint.setStartDate(list.getStartDate());
				dblist.add(sprint);
			});
			reult=sprintRepository.save(dblist);
		}
		else if(sl.getSprintValues().size()>sprintCount){
			
			int noOfSprints = sl.getSprintValues().size()-sprintCount;
			for(int i=1; i<=noOfSprints; i++){
				System.out.println("i value >>>>>>>>"+i);
				Sprint s = sl.getSprintValues().get(sl.getSprintValues().size()-i);
				Sprint sprint = new Sprint();
				sprint.setProject(project);
				sprint.setJiraSprintId(s.getJiraSprintId());
				sprint.setCompleteDate(s.getCompleteDate());
				sprint.setEndDate(s.getEndDate());
				sprint.setSprintName(s.getSprintName());
				sprint.setSprintState(s.getSprintState());
				sprint.setStartDate(s.getStartDate());
				dblist.add(sprint);
			}
			reult=sprintRepository.save(dblist);
		}
		System.err.println(".....service impl ......");
		return reult;
	}
}
