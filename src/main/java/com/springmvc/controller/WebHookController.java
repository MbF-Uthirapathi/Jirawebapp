package com.springmvc.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.model.WebhookEventRequest;
import com.springmvc.service.WebHookService;

@Controller
public class WebHookController {

	@Autowired
	private WebHookService webHookService;
	
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@POST
	@RequestMapping(value = "/mvc/webhook")
	public String issueUpdateNotification(@RequestBody WebhookEventRequest eventReqest){
		String result = webHookService.updateIssueNotification(eventReqest);
		System.err.println("result--------------->"+result);
		return "welcome";
	}
}
