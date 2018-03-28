package com.springmvc.repository.custom;

import java.util.List;

import com.springmvc.model.User;

public interface UserRepositoryCustom {

	//List<User> findByFirstnameOrLastname(String firstname, String lastname);
    List<User> findByFirstnameOrLastname(String firstname, String lastname);

	
}
