package com.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Issuetype {

	@JsonProperty("name")
	private String issuetypename;
	@JsonProperty("subtask")
	private boolean subtask;
}
