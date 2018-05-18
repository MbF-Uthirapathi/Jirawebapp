package com.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubtaskFields {

	@JsonProperty("id")
	private String subtaskId;
	
	@JsonProperty("id")
	private SprintStatus subtaskStatus;
}
