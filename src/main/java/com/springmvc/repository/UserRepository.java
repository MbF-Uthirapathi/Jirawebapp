package com.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvc.model.User;
import com.springmvc.repository.custom.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

    List<User> findByTeamDetailsTeamId(Object id);

}
