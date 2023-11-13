package com.example.projectmanageclientmvc.controller;

import com.example.projectmanageclientmvc.model.Project;
import com.example.projectmanageclientmvc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/projects")
public class AddProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/add")
    public String showAddProjectForm(Model model){
        Project project = new Project();
        model.addAttribute("project", project);
        return "add-project";
    }

    @PostMapping("/add")
    public String addProject(@ModelAttribute Project project) {
        System.out.println(project.getName()+" "+project.getDescription());
        projectService.addProjects(project);
        return "redirect:/projects";
    }
}