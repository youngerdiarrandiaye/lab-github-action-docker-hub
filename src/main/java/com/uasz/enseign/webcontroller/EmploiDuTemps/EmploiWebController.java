package com.uasz.enseign.webcontroller.EmploiDuTemps;

import com.uasz.enseign.dto.EmploiDuTemps.EmploiDTO;
import com.uasz.enseign.service.EmploiDuTemps.EmploiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/emplois")
public class EmploiWebController {

    private final EmploiService emploiService;

    @Autowired
    public EmploiWebController(EmploiService emploiService) {
        this.emploiService = emploiService;
    }

    @GetMapping
    public String getAllEmplois(Model model) {
        List<EmploiDTO> emplois = emploiService.getAllEmplois();
        model.addAttribute("emplois", emplois);
        return "emploi/list"; // Remplacez "emploi/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getEmploiById(@PathVariable Long id, Model model) {
        EmploiDTO emploi = emploiService.getEmploiById(id);
        model.addAttribute("emploi", emploi);
        return "emploi/details"; // Remplacez "emploi/details" par le chemin réel de votre vue de détails
    }

    @GetMapping("/create")
    public String showCreateEmploiForm(Model model) {
        model.addAttribute("emploiDTO", new EmploiDTO());
        return "emploi/create"; // Remplacez "emploi/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createEmploi(@ModelAttribute("emploiDTO") EmploiDTO emploiDTO) {
        emploiService.createEmploi(emploiDTO);
        return "redirect:/emplois"; // Redirige vers la liste des emplois après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditEmploiForm(@PathVariable Long id, Model model) {
        EmploiDTO emploi = emploiService.getEmploiById(id);
        model.addAttribute("emploi", emploi);
        return "emploi/edit"; // Remplacez "emploi/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editEmploi(@PathVariable Long id, @ModelAttribute("emploiDTO") EmploiDTO emploiDTO) {
        emploiService.updateEmploi(id, emploiDTO);
        return "redirect:/emplois"; // Redirige vers la liste des emplois après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteEmploiForm(@PathVariable Long id, Model model) {
        EmploiDTO emploi = emploiService.getEmploiById(id);
        model.addAttribute("emploi", emploi);
        return "emploi/delete"; // Remplacez "emploi/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteEmploi(@PathVariable Long id) {
        emploiService.deleteEmploi(id);
        return "redirect:/emplois"; // Redirige vers la liste des emplois après la suppression
    }
}
