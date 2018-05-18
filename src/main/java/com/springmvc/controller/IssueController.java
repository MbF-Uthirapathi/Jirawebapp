package com.springmvc.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.JiraRequest;
import com.springmvc.service.IssueService;

@Controller
public class IssueController {

	@Resource
    private IssueService issueService;

	@RequestMapping(value = "/issueJsp")
    public ModelAndView browser() {
        return new ModelAndView("jiraIssue");
    }
	
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    public ModelAndView listIssue(JiraRequest user, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.err.println(".........."+user.getFromDate());
    	model.addAttribute("jsonList", issueService.findIssue(user));
        return new ModelAndView("excel");
    }
}
