package com.example.projectmanageclientmvc.controller;

import com.example.projectmanageclientmvc.TokenHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/add")
    public String showAddProjectForm(Model model) {
        return "add-project";
    }

    @GetMapping("/manage")
    public String showManageProjects(Model model) {
        return "project-view";
    }

    @GetMapping("/")
    public String index(@RegisteredOAuth2AuthorizedClient("public-client") OAuth2AuthorizedClient authorizedClient) {
        TokenHolder.setToken(authorizedClient.getAccessToken());
        return "redirect:/projects";
    }




}