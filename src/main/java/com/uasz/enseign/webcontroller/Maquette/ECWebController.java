package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.ECDTO;
import com.uasz.enseign.service.Maquette.ECService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ec")
public class ECWebController {

    private final ECService ecService;

    @Autowired
    public ECWebController(ECService ecService) {
        this.ecService = ecService;
    }

    // Affiche la liste de tous les ECs
    @GetMapping
    public String getAllECs(Model model) {
        List<ECDTO> ecs = ecService.getAllECs();
        model.addAttribute("ecs", ecs);
        return "ec/list"; // Renvoie à la vue de liste des ECs
    }

    // Affiche les détails d'un EC spécifique
    @GetMapping("/{id}")
    public String getECById(@PathVariable Long id, Model model) {
        ECDTO ec = ecService.getECById(id);
        model.addAttribute("ec", ec);
        return "ec/view"; // Renvoie à la vue d'affichage des détails d'un EC
    }

    // Affiche le formulaire de création d'un nouveau EC
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ec", new ECDTO());
        return "ec/create"; // Renvoie au formulaire de création d'un nouveau EC
    }

    // Traite la création d'un nouveau EC
    @PostMapping("/new")
    public String createEC(@ModelAttribute("ec") ECDTO ecDTO) {
        ecService.createEC(ecDTO);
        return "redirect:/ec"; // Redirige vers la liste des ECs après la création
    }

    // Affiche le formulaire d'édition d'un EC existant
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ECDTO ec = ecService.getECById(id);
        model.addAttribute("ec", ec);
        return "ec/edit"; // Renvoie au formulaire d'édition d'un EC
    }

    // Traite la mise à jour d'un EC existant
    @PostMapping("/{id}/edit")
    public String updateEC(@PathVariable Long id, @ModelAttribute("ec") ECDTO ecDTO) {
        ecService.updateEC(id, ecDTO);
        return "redirect:/ec"; // Redirige vers la liste des ECs après la mise à jour
    }

    // Affiche le formulaire de confirmation de suppression d'un EC
    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        ECDTO ec = ecService.getECById(id);
        model.addAttribute("ec", ec);
        return "ec/delete"; // Renvoie au formulaire de confirmation de suppression
    }

    // Traite la suppression d'un EC
    @PostMapping("/{id}/delete")
    public String deleteEC(@PathVariable Long id) {
        ecService.deleteEC(id);
        return "redirect:/ec"; // Redirige vers la liste des ECs après la suppression
    }
}
