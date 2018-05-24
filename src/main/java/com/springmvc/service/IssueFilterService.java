package com.springmvc.service;

import java.util.List;

import com.springmvc.model.IssueFilter;
import com.springmvc.model.IssueFilterList;

public interface IssueFilterService {

	public List<IssueFilterList> getIssuesWithStatus(IssueFilter user);	

	
}
