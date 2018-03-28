package com.springmvc.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.springmvc.model.Team;
import com.springmvc.repository.TeamRespository;
import com.springmvc.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
    @Resource
    private TeamRespository teamRespository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Team createTeam(Team team) {
        Team createdUser = team;
        return teamRespository.save(createdUser);
    }

    @Override
    public Team validateUser(Team team) {

        String sql = "select * from team where teamLoginName='" + team.getTeamLoginName() + "' and teamLoginPwd='"
                + team.getTeamLoginPwd() + "'";
        List<Team> users = jdbcTemplate.query(sql, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;

    }

    class UserMapper implements RowMapper<Team> {

        public Team mapRow(ResultSet rs, int arg1) throws SQLException {
            Team user = new Team();
            user.setTeamLoginName((rs.getString("teamLoginName")));
            user.setTeamLoginPwd((rs.getString("teamLoginPwd")));
            user.setTeamName((rs.getString("teamName")));
            user.setTeamId((rs.getInt("teamId")));
            return user;
        }
    }

}
