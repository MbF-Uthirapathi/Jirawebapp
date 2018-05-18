package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Sprint;

public interface SprintService {

	List<Sprint> createSprints(int projectId);
}
