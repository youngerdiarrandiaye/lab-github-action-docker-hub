package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.FormationDTO;
import com.uasz.enseign.service.Maquette.FormationService;
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
@RequestMapping("/formations")
public class FormationWebController {

    private final FormationService formationService;

    @Autowired
    public FormationWebController(FormationService formationService) {
        this.formationService = formationService;
    }

    @GetMapping
    public String getAllFormations(Model model) {
        List<FormationDTO> formations = formationService.getAllFormations();
        model.addAttribute("formations", formations);
        return "formation/list"; // Remplacez "formation/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getFormationById(@PathVariable Long id, Model model) {
        FormationDTO formationDTO = formationService.getFormationById(id);
        if (formationDTO != null) {
            model.addAttribute("formation", formationDTO);
            return "formation/details"; // Remplacez "formation/details" par le chemin réel de votre vue de détails
        } else {
            return "formation/notFound"; // Remplacez "formation/notFound" par le chemin réel de votre vue pour les ressources non trouvées
        }
    }

    @GetMapping("/create")
    public String showCreateFormationForm(Model model) {
        model.addAttribute("formationDTO", new FormationDTO());
        return "formation/create"; // Remplacez "formation/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createFormation(@ModelAttribute("formationDTO") FormationDTO formationDTO) {
        formationService.createFormation(formationDTO);
        return "redirect:/formations"; // Redirige vers la liste des formations après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditFormationForm(@PathVariable Long id, Model model) {
        FormationDTO formationDTO = formationService.getFormationById(id);
        model.addAttribute("formationDTO", formationDTO);
        return "formation/edit"; // Remplacez "formation/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editFormation(@PathVariable Long id, @ModelAttribute("formationDTO") FormationDTO formationDTO) {
        formationService.updateFormation(id, formationDTO);
        return "redirect:/formations"; // Redirige vers la liste des formations après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteFormationForm(@PathVariable Long id, Model model) {
        FormationDTO formationDTO = formationService.getFormationById(id);
        model.addAttribute("formationDTO", formationDTO);
        return "formation/delete"; // Remplacez "formation/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
        return "redirect:/formations"; // Redirige vers la liste des formations après la suppression
    }

    @GetMapping("/filiere/{filiereId}")
    public String getFormationsByFiliereId(@PathVariable Long filiereId, Model model) {
        List<FormationDTO> formations = formationService.getFormationsByFiliereId(filiereId);
        model.addAttribute("formations", formations);
        return "formation/list"; // Remplacez "formation/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/niveau/{niveauId}")
    public String getFormationsByNiveauId(@PathVariable Long niveauId, Model model) {
        List<FormationDTO> formations = formationService.getFormationsByNiveauId(niveauId);
        model.addAttribute("formations", formations);
        return "formation/list"; // Remplacez "formation/list" par le chemin réel de votre vue de liste
    }
}
