package com.springmvc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Fields {

	@JsonProperty("issuetype")
	private Issuetype issuetype;
	@JsonProperty("project")
	private SprintProject project;
	@JsonProperty("assignee")
	private Assignee assignee;
	@JsonProperty("status")
	private SprintStatus status;
	@JsonProperty("subtasks")
	private List<Subtask> subtasks;
	@JsonProperty("timetracking")
	private TimeTracking timeTracking;
	
}
