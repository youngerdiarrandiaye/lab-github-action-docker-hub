package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.NiveauDTO;
import com.uasz.enseign.service.Maquette.NiveauService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Slf4j
@Controller
@RequestMapping("/niveaux")
public class NiveauWebController {

    private final NiveauService niveauService;

    @Autowired
    public NiveauWebController(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @GetMapping
    public String getAllNiveaux(Model model) {
        List<NiveauDTO> niveaux = niveauService.getAllNiveaux();
        model.addAttribute("niveaux", niveaux);
        return "niveau/list"; // Remplacez "niveau/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getNiveauById(@PathVariable Long id, Model model) {
        NiveauDTO niveauDTO = niveauService.getNiveauById(id);
        if (niveauDTO != null) {
            model.addAttribute("niveau", niveauDTO);
            return "niveau/details"; // Remplacez "niveau/details" par le chemin réel de votre vue de détails
        } else {
            return "niveau/notFound"; // Remplacez "niveau/notFound" par le chemin réel de votre vue pour les ressources non trouvées
        }
    }

    @GetMapping("/create")
    public String showCreateNiveauForm(Model model) {
        model.addAttribute("niveauDTO", new NiveauDTO());
        return "niveau/create"; // Remplacez "niveau/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createNiveau(@ModelAttribute("niveauDTO") NiveauDTO niveauDTO) {
        niveauService.createNiveau(niveauDTO);
        return "redirect:/niveaux"; // Redirige vers la liste des niveaux après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditNiveauForm(@PathVariable Long id, Model model) {
        NiveauDTO niveauDTO = niveauService.getNiveauById(id);
        model.addAttribute("niveauDTO", niveauDTO);
        return "niveau/edit"; // Remplacez "niveau/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editNiveau(@PathVariable Long id, @ModelAttribute("niveauDTO") NiveauDTO niveauDTO) {
        niveauService.updateNiveau(id, niveauDTO);
        return "redirect:/niveaux"; // Redirige vers la liste des niveaux après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteNiveauForm(@PathVariable Long id, Model model) {
        NiveauDTO niveauDTO = niveauService.getNiveauById(id);
        model.addAttribute("niveauDTO", niveauDTO);
        return "niveau/delete"; // Remplacez "niveau/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteNiveau(@PathVariable Long id) {
        niveauService.deleteNiveau(id);
        return "redirect:/niveaux"; // Redirige vers la liste des niveaux après la suppression
    }

    @GetMapping("/cycle/{cycleId}")
    public String getNiveauxByCycleId(@PathVariable Long cycleId, Model model) {
        List<NiveauDTO> niveaux = niveauService.getNiveauxByCycleId(cycleId);
        model.addAttribute("niveaux", niveaux);
        return "niveau/list"; // Remplacez "niveau/list" par le chemin réel de votre vue de liste
    }
}
