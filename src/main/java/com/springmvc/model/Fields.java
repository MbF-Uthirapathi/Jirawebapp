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
	private IssueStatus status;
	@JsonProperty("subtasks")
	private List<Subtask> subtasks;
	@JsonProperty("created")
	private String createdDate;
	@JsonProperty("summary")
	private String summary;
	@JsonProperty("timetracking")
	private TimeTrack timeTrack;
	@JsonProperty("sprint")
	private Sprint sprint;
}
