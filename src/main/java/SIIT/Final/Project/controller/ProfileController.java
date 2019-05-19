package SIIT.Final.Project.controller;

import SIIT.Final.Project.model.User;
import SIIT.Final.Project.service.ProfileService;
import SIIT.Final.Project.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping(path = "")
    public ModelAndView getProfileInformations(@RequestParam Integer id){
        ModelAndView pi = new ModelAndView();
        pi.setViewName("profile");
        pi.addObject("profile", profileService.getProfile(id));
        return pi;
    }

    @PostMapping("")
    public String profileSubmit(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        profileService.saveProfile(user);
        return "redirect:/profile";
    }
}