package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmvc.model.Team;
import com.springmvc.service.TeamService;

@Controller
public class TeamController {
    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public ModelAndView newteamPage() {
        ModelAndView mav = new ModelAndView("team", "team", new Team());
        return mav;
    }

    @RequestMapping(value = "/team", method = RequestMethod.POST)
    public ModelAndView teamUser(@ModelAttribute Team team, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String message = "New team  " + team.getTeamLoginName() + " " + team.getTeamName()
                + " was successfully created.";
        teamService.createTeam(team);
        mav.setViewName("redirect:/home");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public String loginview() {
        return "welcome";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ModelAndView loginSuccess(final RedirectAttributes redirectAttributes, Team team, HttpServletRequest request,
            HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();
        Team user = teamService.validateUser(team);

        if (null != user) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getTeamId());
            mav = new ModelAndView("redirect:/Success");
            mav.addObject("teamName", user.getTeamName());
        } else {
            mav = new ModelAndView("redirect:/home");
            mav.addObject("message", "Username or Password is wrong!!");
        }
        return mav;
    }

    @RequestMapping("/Success")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String view() {
        return "home";
    }

}
