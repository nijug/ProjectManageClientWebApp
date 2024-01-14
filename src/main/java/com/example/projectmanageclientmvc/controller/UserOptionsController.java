package com.example.projectmanageclientmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;

@Controller
public class UserOptionsController {
    @RequestMapping("/changeLanguage")
    public String changeLanguage(String lang, HttpServletRequest request, HttpServletResponse response) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if ("pl".equals(lang)) {
            localeResolver.setLocale(request, response, Locale.forLanguageTag("pl-PL"));
        } else {
            localeResolver.setLocale(request, response, Locale.ENGLISH);
        }
        return "redirect:/";
    }
}
