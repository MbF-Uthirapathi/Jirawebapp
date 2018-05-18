package com.springmvc.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.springmvc.model.JiraRequest;
import com.springmvc.model.JiraUser;

public interface JiraUserService {

/*    List<JiraUser> findAll(Object id);
*/

    JiraUser create(JiraUser user);

    JiraUser update(JiraUser user);

    JiraUser delete(int jiraId);

    JiraUser findById(int jiraId);

    List<String> findIssue(JiraRequest user, HttpSession session) throws UnsupportedEncodingException;

    List<JiraUser> findAll();

}
