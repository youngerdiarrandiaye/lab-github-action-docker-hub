package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.EnseignementDTO;
import com.uasz.enseign.service.Maquette.EnseignementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enseignements")
public class EnseignementWebController {

    private final EnseignementService enseignementService;

    @Autowired
    public EnseignementWebController(EnseignementService enseignementService) {
        this.enseignementService = enseignementService;
    }

    @GetMapping
    public String getAllEnseignements(Model model) {
        List<EnseignementDTO> enseignements = enseignementService.getAllEnseignements();
        model.addAttribute("enseignements", enseignements);
        return "enseignement/list"; // Remplacez "enseignement/list" par le chemin réel de votre vue
    }

    @GetMapping("/{id}")
    public String getEnseignementById(@PathVariable Long id, Model model) {
        EnseignementDTO enseignement = enseignementService.getEnseignementById(id);
        model.addAttribute("enseignement", enseignement);
        return "enseignement/view"; // Remplacez "enseignement/view" par le chemin réel de votre vue
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("enseignement", new EnseignementDTO());
        return "enseignement/create"; // Remplacez "enseignement/create" par le chemin réel de votre vue
    }

    @PostMapping("/new")
    public String createEnseignement(@ModelAttribute("enseignement") EnseignementDTO enseignementDTO) {
        enseignementService.createEnseignement(enseignementDTO);
        return "redirect:/enseignements";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        EnseignementDTO enseignement = enseignementService.getEnseignementById(id);
        model.addAttribute("enseignement", enseignement);
        return "enseignement/edit"; // Remplacez "enseignement/edit" par le chemin réel de votre vue
    }

    @PostMapping("/{id}/edit")
    public String updateEnseignement(@PathVariable Long id, @ModelAttribute("enseignement") EnseignementDTO enseignementDTO) {
        enseignementService.updateEnseignement(id, enseignementDTO);
        return "redirect:/enseignements";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        EnseignementDTO enseignement = enseignementService.getEnseignementById(id);
        model.addAttribute("enseignement", enseignement);
        return "enseignement/delete"; // Remplacez "enseignement/delete" par le chemin réel de votre vue
    }

    @PostMapping("/{id}/delete")
    public String deleteEnseignement(@PathVariable Long id) {
        enseignementService.deleteEnseignement(id);
        return "redirect:/enseignements";
    }

    @GetMapping("/classe/{classeId}")
    public String getEnseignementsByClasseId(@PathVariable Long classeId, Model model) {
        List<EnseignementDTO> enseignementsByClasseId = enseignementService.getEnseignementsByClasseId(classeId);
        model.addAttribute("enseignements", enseignementsByClasseId);
        return "enseignement/list"; // Remplacez "enseignement/list" par le chemin réel de votre vue
    }

    @GetMapping("/groupe/{groupeId}")
    public String getEnseignementsByGroupeId(@PathVariable Long groupeId, Model model) {
        List<EnseignementDTO> enseignementsByGroupeId = enseignementService.getEnseignementsByGroupeId(groupeId);
        model.addAttribute("enseignements", enseignementsByGroupeId);
        return "enseignement/list"; // Remplacez "enseignement/list" par le chemin réel de votre vue
    }
}
