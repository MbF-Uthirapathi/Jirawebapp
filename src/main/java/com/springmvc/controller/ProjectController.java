package com.springmvc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmvc.model.Project;
import com.springmvc.service.ProjectService;

@Controller
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public ModelAndView newUserPage() {
        ModelAndView mav = new ModelAndView("projectsetup", "user", new Project());
        List<Project> userList = projectService.findAll();
        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public @ResponseBody ModelAndView addUser(@ModelAttribute Project user,
            final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("projectsetup", "user", new Project());
        System.out.println("Project Status ...."+user.getProjectStatus());
        String message = "New user " + user.getProjectName() + " " + " was successfully created.";
        projectService.create(user);
        System.out.println("........"+message);
        List<Project> userList = projectService.findAll();
        mav.addObject("userList", userList);
        mav.addObject("message", message);
        /*
         * mav.setViewName("redirect:/projectList");
         */ redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/projectList", method = RequestMethod.GET)
    public ModelAndView userListPage(HttpSession session) {
        ModelAndView mav = new ModelAndView("projectList");
        /*
         * session.getAttribute("user"); Object id
         * =session.getAttribute("user");
         */
        List<Project> userList = projectService.findAll();
        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping(value = "/projectEdit/{projectId}", method = RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Integer projectId) {
        ModelAndView mav = new ModelAndView("projectEdit");
        Project user = projectService.findById(projectId);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/projectEdit/{projectId}", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute Project user, @PathVariable Integer projectId,
            final RedirectAttributes redirectAttributes, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/project");
        Project userDetails = projectService.findById(projectId);
        user.setPassword(userDetails.getPassword());
        projectService.update(user);
        String message = "User " + user.getProjectName() + " was successfully updated.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/projectDelete/{projectId}", method = RequestMethod.GET)
    public ModelAndView deleteShop(@PathVariable Integer projectId, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/project");
        Project user = projectService.delete(projectId);
        String message = "User " + user.getProjectName() + " was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }
}
