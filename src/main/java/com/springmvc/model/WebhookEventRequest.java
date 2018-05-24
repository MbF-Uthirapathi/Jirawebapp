package com.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class WebhookEventRequest {
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("timestamp")
	private long timeStamp;
	
	@JsonProperty("webhookEvent")
	private String webhookEvent;
	
	/*@JsonProperty("issue_event_type_name")
	private String issueEventType;*/
	
	@JsonProperty("issue")
	private Issue issue;
	
	/*@JsonProperty("user")
	private String user;
	
	@JsonProperty("changelog")
	private String changelog;
	
	@JsonProperty("comment")
	private String comment;*/
	
}
