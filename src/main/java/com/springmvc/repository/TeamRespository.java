package com.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvc.model.Team;

public interface TeamRespository  extends JpaRepository<Team, Integer>{


}
