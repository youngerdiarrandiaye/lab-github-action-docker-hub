package com.uasz.enseign.webcontroller.EmploiDuTemps;

import com.uasz.enseign.dto.EmploiDuTemps.SalleDTO;
import com.uasz.enseign.service.EmploiDuTemps.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/salles")
public class SalleWebController {

    private final SalleService salleService;

    @Autowired
    public SalleWebController(SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping
    public String getAllSalles(Model model) {
        List<SalleDTO> salles = salleService.getAllSalles();
        model.addAttribute("salles", salles);
        return "salle/list"; // Remplacez "salle/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getSalleById(@PathVariable Long id, Model model) {
        SalleDTO salle = salleService.getSalleById(id);
        model.addAttribute("salle", salle);
        return "salle/details"; // Remplacez "salle/details" par le chemin réel de votre vue de détails
    }

    @GetMapping("/create")
    public String showCreateSalleForm(Model model) {
        model.addAttribute("salleDTO", new SalleDTO());
        return "salle/create"; // Remplacez "salle/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createSalle(@ModelAttribute("salleDTO") SalleDTO salleDTO) {
        salleService.createSalle(salleDTO);
        return "redirect:/salles"; // Redirige vers la liste des salles après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditSalleForm(@PathVariable Long id, Model model) {
        SalleDTO salle = salleService.getSalleById(id);
        model.addAttribute("salle", salle);
        return "salle/edit"; // Remplacez "salle/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editSalle(@PathVariable Long id, @ModelAttribute("salleDTO") SalleDTO salleDTO) {
        salleService.updateSalle(id, salleDTO);
        return "redirect:/salles"; // Redirige vers la liste des salles après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteSalleForm(@PathVariable Long id, Model model) {
        SalleDTO salle = salleService.getSalleById(id);
        model.addAttribute("salle", salle);
        return "salle/delete"; // Remplacez "salle/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteSalle(@PathVariable Long id) {
        salleService.deleteSalle(id);
        return "redirect:/salles"; // Redirige vers la liste des salles après la suppression
    }
}
