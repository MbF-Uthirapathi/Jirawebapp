package com.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class TimeTracking {
	@JsonProperty("remainingEstimate")
	private String remainingEstimate;
	@JsonProperty("originalEstimate")
	private String originalEstimate;
	@JsonProperty("timeSpent")
	private String timeSpent;
}
