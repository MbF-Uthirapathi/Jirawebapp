package com.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springmvc.model.IssueDetail;
@Repository
public interface IssueRepository extends JpaRepository<IssueDetail, Integer> {

}
