package com.example.projectmanageclientmvc.controller;

import com.example.projectmanageclientmvc.TokenHolder;
import jakarta.validation.Valid;
import org.ProjectService.Project;
import org.ProjectService.ProjectService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/projects")
public class ProjectViewController {

    private ProjectService projectService;


    public void init() {
        OAuth2AccessToken token = TokenHolder.getToken();
        if (token != null) {
            projectService = ProjectService.getInstance(token.getTokenValue());
        }
    }

    @GetMapping
    public String showManageProjects(Model model) {
        init();
        Project[] projects = projectService.readProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("siteTitle", "Manage Projects");
        return "project-view";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("siteTitle", "Edit Project");
        model.addAttribute("project", project);
        return "edit-project";
    }


    @PostMapping("/edit")
    public String updateProject(@Valid @ModelAttribute Project project, BindingResult bindingResult) {
        System.out.println("Start Date: " + project.getDateStarted());
        System.out.println("End Date: " + project.getDateEnded());
        if (bindingResult.hasErrors()) {
            System.out.println("Error: " + bindingResult.getAllErrors());
            return "edit-project";
        }
        projectService.updateProjects(project);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Integer id) {
        projectService.deleteProjects(id);
        return "redirect:/projects";
    }
}