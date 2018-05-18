package com.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectInfo {

	private String projectName;
	private String projectKey;
	private int sprintId;
	private String sprintName;
	private long toDoCount;
	private long inProgressCount;
	private long doneCount;
	private long newCount;
	private long approveCount;
	private long reOpenCount;
	private long closedCount;
	private int weekOfSprint;
}
