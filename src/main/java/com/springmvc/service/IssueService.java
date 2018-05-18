package com.springmvc.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.springmvc.model.JiraRequest;

public interface IssueService {

	List<String> findIssue(JiraRequest user) throws UnsupportedEncodingException;
}
