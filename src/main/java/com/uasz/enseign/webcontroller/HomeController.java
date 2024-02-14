package com.uasz.enseign.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String showHomePage(Model model) {
        model.addAttribute("pageTitle", "Accueil");

        // Remplacez "home/index" par le chemin réel de votre vue de page d'accueil
        return "home";
    }
}
