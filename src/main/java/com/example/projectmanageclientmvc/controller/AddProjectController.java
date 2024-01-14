package com.example.projectmanageclientmvc.controller;

import com.example.projectmanageclientmvc.TokenHolder;
import jakarta.annotation.PostConstruct;
import org.ProjectService.Project;
import org.ProjectService.ProjectService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/projects")
public class AddProjectController {


    private ProjectService projectService;


    public void init() {
        OAuth2AccessToken token = TokenHolder.getToken();
        if (token != null) {
            projectService = ProjectService.getInstance(token.getTokenValue());
        }
    }

    @GetMapping("/add")
    public String showAddProjectForm(Model model){
        init();
        Project project = new Project();
        model.addAttribute("project", project);
        model.addAttribute("siteTitle", "Add Project");
        return "add-project";
    }

    @PostMapping("/add")
    public String addProject(@Valid @ModelAttribute Project project, BindingResult bindingResult) {
        System.out.println("Start Date: " + project.getDateStarted());
        System.out.println("End Date: " + project.getDateEnded());

        if (bindingResult.hasErrors()) {
            System.out.println("Error: " + bindingResult.getAllErrors());
            return "add-project";
        }
        projectService.addProjects(project);
        return "redirect:/";
    }
}