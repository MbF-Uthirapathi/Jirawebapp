package com.springmvc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardResponse {
	
	@JsonProperty("total")
	private int total;
	@JsonProperty("values")
	private List<BoardDetails> values;

}
