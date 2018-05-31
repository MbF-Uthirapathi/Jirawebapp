package com.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class Issue {
	
	@JsonProperty("id")
	private String issueId;
	
	@JsonProperty("key")
	private String issueKey;
	
	@JsonProperty("fields")
	private Fields fields;
	
	@JsonProperty("summary")
	private String summary;
	
}
