package com.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmvc.model.Team;
import com.springmvc.model.User;
import com.springmvc.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String view() {
        return "home";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "home";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView userListPage(HttpSession session) {
        ModelAndView mav = new ModelAndView("list");
        session.getAttribute("user");
        Object id = session.getAttribute("user");
        List<User> userList = userService.findAll(id);
        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView userSearchPage(@RequestParam(value = "searchstring", required = false) String s) {
        ModelAndView mav = new ModelAndView("list");
        List<User> userList = userService.search(s);

        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newUserPage() {
        ModelAndView mav = new ModelAndView("form", "user", new User());
        List<Team> teamName = userService.findAll();

        mav.addObject("userList", teamName);

        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User addUser, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String message = "New user " + addUser.getFirstname() + " " + addUser.getLastname()
                + " was successfully created.";
        userService.create(addUser);
        mav.setViewName("redirect:/list");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Integer userId) {
        ModelAndView mav = new ModelAndView("edit");
        List<Team> teamName = userService.findAll();

        User user = userService.findById(userId);
        /*
         * user.setTeamDetails(user.getTeamDetails());
         */ mav.addObject("user", user);
        mav.addObject("userList", teamName);
        return mav;
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user, @PathVariable Integer userId,
            final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/list");

        /*
         * User userDetails = userService.findById(userId);
         * user.setTeamDetails(userDetails.getTeamDetails());
         */ userService.update(user);
        String message = "User " + user.getFirstname() + " " + user.getLastname() + " was successfully updated.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public ModelAndView deleteShop(@PathVariable Integer id, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/list");
        User user = userService.delete(id);
        String message = "User " + user.getFirstname() + " " + user.getLastname() + " was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

}
