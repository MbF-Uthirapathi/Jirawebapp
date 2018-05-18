package com.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.springmvc.service.WebHookService;

@Service
public class WebHookServiceImpl implements WebHookService{

	@Override
	public String updateIssueNotification() {
		System.out.println("WebHook...................................");
		return "WbHook--------------------->>>>>>>>>>";
	}

}
