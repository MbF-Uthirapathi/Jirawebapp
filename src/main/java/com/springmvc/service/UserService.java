package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Team;
import com.springmvc.model.User;

public interface UserService {

	public User create(User addUser);
    public User delete(int id);
    public List<User> findAll(Object id);
    public User update(User user);
    public User findById(int id);
    public List<User> search(String s);
    public List<Team> findAll();
	
}
