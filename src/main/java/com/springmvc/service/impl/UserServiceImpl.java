package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.model.Team;
import com.springmvc.model.User;
import com.springmvc.repository.TeamRespository;
import com.springmvc.repository.UserRepository;
import com.springmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserRepository userRepository;
    @Resource
    private TeamRespository teamRespository;

    @Override
    @Transactional
    public User create(User addUser) {
        User createdUser = addUser;
        return userRepository.save(createdUser);
    }

    @Override
    @Transactional
    public User findById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public User delete(int id) {
        User deletedUser = userRepository.findOne(id);
        userRepository.delete(deletedUser);
        return deletedUser;
    }

    @Override
    @Transactional
    public List<User> findAll(Object id) {

        return userRepository.findByTeamDetailsTeamId(id);
    }

    @Override
    @Transactional
    public List<Team> findAll() {

        return teamRespository.findAll();
    }

    
    @Override
    @Transactional
    public User update(User user) {
        logger.info(user.getUserId() + user.getFirstname() + user.getLastname() + user.getDesignation());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> search(String s) {
        return userRepository.findByFirstnameOrLastname(s, s);
    }

}