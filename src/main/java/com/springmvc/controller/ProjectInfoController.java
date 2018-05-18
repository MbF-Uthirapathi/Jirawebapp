package com.springmvc.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.service.ProjectInfoService;

@Controller
public class ProjectInfoController {

	@Resource
    private ProjectInfoService projectInfoService;
	
	@RequestMapping(value = "/projectsInfoJsp")
    public ModelAndView viewProjectInfo() {
        return new ModelAndView("projectsInfo");
    }
	
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/projectsInfo", method = RequestMethod.GET)
    public ModelAndView projectsInfo(@PathParam("date") String date,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	ModelAndView mav = new ModelAndView("projectsInfo");
    	System.err.println(".......user...."+date);
    	mav.addObject("projectInfoList", projectInfoService.findIssuesCount(date));
    	return mav;
    }
}