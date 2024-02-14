package com.uasz.enseign.webcontroller.EmploiDuTemps;

import com.uasz.enseign.dto.EmploiDuTemps.BatimentDTO;
import com.uasz.enseign.service.EmploiDuTemps.BatimentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("")
public class BatimentWebController {

    private final BatimentService batimentService;

    @Autowired
    public BatimentWebController(BatimentService batimentService) {
        this.batimentService = batimentService;
    }

    // Affiche la liste de tous les batiments
    @GetMapping("/list")
    public String showAllBatiments(Model model) {
        List<BatimentDTO> batimentList = batimentService.getAllBatiments();
        model.addAttribute("batiments", batimentList);
        return "batiment/list"; // Remplacez "batiment/list" par le chemin réel de votre vue
    }

    // Affiche les détails d'un batiment par son ID
    @GetMapping("/{id}")
    public String showBatimentDetails(@PathVariable Long id, Model model) {
        BatimentDTO batimentDTO = batimentService.getBatimentById(id);
        model.addAttribute("batiment", batimentDTO);
        return "batiment/details"; // Remplacez "batiment/details" par le chemin réel de votre vue
    }

    // Affiche le formulaire de création d'un nouveau batiment
    @GetMapping("/new")
    public String showCreateBatimentForm(Model model) {
        model.addAttribute("batiment", new BatimentDTO());
        return "batiment/create"; // Remplacez "batiment/create" par le chemin réel de votre vue
    }

    // Traite la soumission du formulaire de création d'un nouveau batiment
    @PostMapping("/new")
    public String createBatiment(@ModelAttribute("batiment") BatimentDTO batimentDTO) {
        batimentService.createBatiment(batimentDTO);
        return "redirect:/batiments/list";
    }

    // Affiche le formulaire de mise à jour d'un batiment existant par son ID
    @GetMapping("/{id}/edit")
    public String showEditBatimentForm(@PathVariable Long id, Model model) {
        BatimentDTO batimentDTO = batimentService.getBatimentById(id);
        model.addAttribute("batiment", batimentDTO);
        return "batiment/edit"; // Remplacez "batiment/edit" par le chemin réel de votre vue
    }

    // Traite la soumission du formulaire de mise à jour d'un batiment existant par son ID
    @PostMapping("/{id}/edit")
    public String updateBatiment(@PathVariable Long id, @ModelAttribute("batiment") BatimentDTO batimentDTO) {
        batimentService.updateBatiment(id, batimentDTO);
        return "redirect:/batiments/list";
    }

    // Affiche la page de confirmation de suppression d'un batiment par son ID
    @GetMapping("/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        BatimentDTO batimentDTO = batimentService.getBatimentById(id);
        model.addAttribute("batiment", batimentDTO);
        return "batiment/delete"; // Remplacez "batiment/delete" par le chemin réel de votre vue
    }

    // Traite la suppression d'un batiment par son ID
    @PostMapping("/{id}/delete")
    public String deleteBatiment(@PathVariable Long id) {
        batimentService.deleteBatiment(id);
        return "redirect:/batiments/list";
    }
}
