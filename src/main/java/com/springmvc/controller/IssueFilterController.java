package com.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.IssueFilter;
import com.springmvc.service.IssueFilterService;
import com.springmvc.service.ProjectService;

@Controller
public class IssueFilterController {

	@Resource
    private ProjectService projectService;
	
	@Resource
    private IssueFilterService issueFilterService;
	
	@RequestMapping(value = "/issueFilterJsp")
    public ModelAndView viewissueFilter() {
		System.err.println("......");
		ModelAndView mv = new ModelAndView("issueFilter", "user", new IssueFilter());
		List<String> projectList = projectService.getProjectNames();
		Set<String> usersList = projectService.getJiraUsers(projectList);
		mv.addObject("projectNames", projectList);
		mv.addObject("usersNames", usersList);
        return mv;
    }
	
	@RequestMapping(value = "/issueFilterForm", method = RequestMethod.POST)
    public ModelAndView listIssue(IssueFilter user, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.err.println(".........."+user.getFromDate());
		user.getProjectName().forEach(p->System.out.println("inputs---->"+p));
		model.addAttribute("result", issueFilterService.getIssuesWithStatus(user));
        return new ModelAndView("excel");
    }
}
