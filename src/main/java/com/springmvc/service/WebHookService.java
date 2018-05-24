package com.springmvc.service;

import com.springmvc.model.WebhookEventRequest;

public interface WebHookService {

	public String updateIssueNotification(WebhookEventRequest eventReqest);
}
