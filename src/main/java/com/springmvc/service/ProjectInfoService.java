package com.springmvc.service;

import java.util.List;

import com.springmvc.model.ProjectInfo;

public interface ProjectInfoService {

	List<ProjectInfo> findIssuesCount(String date);
}
