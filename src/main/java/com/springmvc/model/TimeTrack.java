package com.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class TimeTrack {
	
	@JsonProperty("remainingEstimate")
	private String remainTime;
	
	@JsonProperty("timeSpent")
	private String timeSpent;
	
	@JsonProperty("originalEstimate")
	private String originalTime;
}
