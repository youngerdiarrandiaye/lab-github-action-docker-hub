package com.uasz.enseign.webcontroller.EmploiDuTemps;

import com.uasz.enseign.dto.EmploiDuTemps.DeroulementDTO;
import com.uasz.enseign.service.EmploiDuTemps.DeroulementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deroulements")
public class DeroulementWebController {

    private final DeroulementService deroulementService;

    @Autowired
    public DeroulementWebController(DeroulementService deroulementService) {
        this.deroulementService = deroulementService;
    }

    @GetMapping
    public String getAllDeroulements(Model model) {
        List<DeroulementDTO> deroulements = deroulementService.getAllDeroulements();
        model.addAttribute("deroulements", deroulements);
        return "deroulement/list"; // Remplacez "deroulement/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getDeroulementById(@PathVariable Long id, Model model) {
        DeroulementDTO deroulement = deroulementService.getDeroulementById(id);
        model.addAttribute("deroulement", deroulement);
        return "deroulement/details"; // Remplacez "deroulement/details" par le chemin réel de votre vue de détails
    }

    @GetMapping("/create")
    public String showCreateDeroulementForm(Model model) {
        model.addAttribute("deroulementDTO", new DeroulementDTO());
        return "deroulement/create"; // Remplacez "deroulement/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createDeroulement(@ModelAttribute("deroulementDTO") DeroulementDTO deroulementDTO) {
        deroulementService.createDeroulement(deroulementDTO);
        return "redirect:/deroulements"; // Redirige vers la liste des déroulements après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditDeroulementForm(@PathVariable Long id, Model model) {
        DeroulementDTO deroulement = deroulementService.getDeroulementById(id);
        model.addAttribute("deroulement", deroulement);
        return "deroulement/edit"; // Remplacez "deroulement/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editDeroulement(@PathVariable Long id, @ModelAttribute("deroulementDTO") DeroulementDTO deroulementDTO) {
        deroulementService.updateDeroulement(id, deroulementDTO);
        return "redirect:/deroulements"; // Redirige vers la liste des déroulements après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteDeroulementForm(@PathVariable Long id, Model model) {
        DeroulementDTO deroulement = deroulementService.getDeroulementById(id);
        model.addAttribute("deroulement", deroulement);
        return "deroulement/delete"; // Remplacez "deroulement/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteDeroulement(@PathVariable Long id) {
        deroulementService.deleteDeroulement(id);
        return "redirect:/deroulements"; // Redirige vers la liste des déroulements après la suppression
    }
}
