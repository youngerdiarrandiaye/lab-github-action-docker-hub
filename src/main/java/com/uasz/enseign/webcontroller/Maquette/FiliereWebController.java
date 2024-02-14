package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.FiliereDTO;
import com.uasz.enseign.service.Maquette.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filieres")
public class FiliereWebController {

    private final FiliereService filiereService;

    @Autowired
    public FiliereWebController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    @GetMapping
    public String showAllFilieres(Model model) {
        List<FiliereDTO> filieres = filiereService.getAllFilieres();
        model.addAttribute("filieres", filieres);
        return "filiere/list"; // Remplacez "filiere/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String showFiliereDetails(@PathVariable Long id, Model model) {
        FiliereDTO filiereDTO = filiereService.getFiliereById(id);
        model.addAttribute("filiere", filiereDTO);
        return "filiere/details"; // Remplacez "filiere/details" par le chemin réel de votre vue de détails
    }

    @GetMapping("/create")
    public String showCreateFiliereForm(Model model) {
        model.addAttribute("filiereDTO", new FiliereDTO());
        return "filiere/create"; // Remplacez "filiere/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createFiliere(@ModelAttribute("filiereDTO") FiliereDTO filiereDTO) {
        filiereService.createFiliere(filiereDTO);
        return "redirect:/filieres"; // Redirige vers la liste des filières après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditFiliereForm(@PathVariable Long id, Model model) {
        FiliereDTO filiereDTO = filiereService.getFiliereById(id);
        model.addAttribute("filiereDTO", filiereDTO);
        return "filiere/edit"; // Remplacez "filiere/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editFiliere(@PathVariable Long id, @ModelAttribute("filiereDTO") FiliereDTO filiereDTO) {
        filiereService.updateFiliere(id, filiereDTO);
        return "redirect:/filieres"; // Redirige vers la liste des filières après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteFiliereForm(@PathVariable Long id, Model model) {
        FiliereDTO filiereDTO = filiereService.getFiliereById(id);
        model.addAttribute("filiereDTO", filiereDTO);
        return "filiere/delete"; // Remplacez "filiere/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteFiliere(@PathVariable Long id) {
        filiereService.deleteFiliere(id);
        return "redirect:/filieres"; // Redirige vers la liste des filières après la suppression
    }
}
