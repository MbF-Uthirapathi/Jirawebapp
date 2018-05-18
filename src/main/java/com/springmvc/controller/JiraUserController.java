package com.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmvc.model.JiraRequest;
import com.springmvc.model.JiraUser;
import com.springmvc.service.JiraUserService;

@XmlRootElement
@Controller
public class JiraUserController {
    @Autowired
    private JiraUserService jiraUserService;

    @RequestMapping(value = "/jiraList", method = RequestMethod.GET)
    public ModelAndView userListPage(HttpSession session) {
        ModelAndView mav = new ModelAndView("jiraList");
        session.getAttribute("user"); 
        /*    Object id =session.getAttribute("user");
        List<JiraUser> userList = jiraUserService.findAll(id);
*/      List<JiraUser> userList = jiraUserService.findAll();  
        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping(value = "/jiraCreate", method = RequestMethod.GET)
    public ModelAndView newUserPage() {
        ModelAndView mav = new ModelAndView("jiraForm", "user", new JiraUser());
/*        List<User> userList = jiraUserService.findAll();
        mav.addObject("userList", userList);*/
        return mav;
    }

    @RequestMapping(value = "/jiraCreate", method = RequestMethod.POST)
    public @ResponseBody ModelAndView addUser(@ModelAttribute JiraUser user,
            final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String message = "New user "  + user.getEmail() + " was successfully created.";
        jiraUserService.create(user);
        
        mav.setViewName("redirect:/jiraList");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/jiraEdit/{jiraId}", method = RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Integer jiraId) {
        ModelAndView mav = new ModelAndView("jiraEdit");
        JiraUser user = jiraUserService.findById(jiraId);
       /* List<User> userList = jiraUserService.findAll();
        mav.addObject("userList", userList);*/
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/jiraEdit/{jiraId}", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute JiraUser user, @PathVariable Integer jiraId,
            final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/jiraList");
        JiraUser userDetails = jiraUserService.findById(jiraId);
/*        user.setPassword(userDetails.getPassword());
*/        /*user.setEmployee(userDetails.getEmployee());*/
        jiraUserService.update(user);
        String message = "User " + user.getEmail() + " was successfully updated.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/jiraDelete/{jiraId}", method = RequestMethod.GET)
    public ModelAndView deleteShop(@PathVariable Integer jiraId, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/jiraList");
       JiraUser user = jiraUserService.delete(jiraId);
       String message = "User  was successfully deleted.";
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/issueissue", method = RequestMethod.POST)
    public ModelAndView listIssue(JiraRequest user, Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
       HttpSession session = request.getSession();
        model.addAttribute("jsonList", jiraUserService.findIssue(user,session));
        return new ModelAndView("excel");
    }

}
