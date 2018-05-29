package com.springmvc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown=true)
public class IssuesList {
	
	@JsonProperty("issues")
	private List<Issue> issues;
	
	@JsonProperty("total")
	private int total;

}
