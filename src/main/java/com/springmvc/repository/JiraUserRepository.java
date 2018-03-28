package com.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvc.model.JiraUser;

public interface JiraUserRepository extends JpaRepository<JiraUser, Integer> {

   /* List<JiraUser> findByEmployeeUserId(int userId);*/

    List<JiraUser> findByJiraId(int jiraId);


 
}
