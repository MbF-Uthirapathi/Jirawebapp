package com.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Assignee {

	@JsonProperty("name")
	private String assigneename;
	@JsonProperty("displayName")
	private String displayName;
	
	@JsonProperty("emailAddress")
	private String assigneeEmailAddress;
	
}
