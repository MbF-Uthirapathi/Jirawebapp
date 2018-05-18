package com.springmvc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.model.Sprint;
import com.springmvc.service.SprintService;

@Controller
public class SprintController {
	
	@Resource
    private SprintService sprintService;

	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/pullAllSprints/{projectId}")
    public String projectsInfo(@PathVariable Integer projectId, HttpServletRequest req, Model model){
    	List<Sprint> result = sprintService.createSprints(projectId);
    	System.err.println(".........controller......"+result.size());
    	model.addAttribute("output", "Data successfully pulled from Jira.");
    	String referer = req.getHeader("Referer");
    	System.err.println("path..."+referer);
    	return "redirect:"+ referer;
    }
}
