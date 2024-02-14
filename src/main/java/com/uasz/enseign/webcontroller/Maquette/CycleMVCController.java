package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.CycleDTO;
import com.uasz.enseign.service.Maquette.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cycles")
public class CycleMVCController {

    private final CycleService cycleService;

    @Autowired
    public CycleMVCController(CycleService cycleService) {
        this.cycleService = cycleService;
    }

    // Affiche la liste de tous les cycles
    @GetMapping
    public String getAllCycles(Model model) {
        List<CycleDTO> cycles = cycleService.getAllCycles();
        model.addAttribute("cycles", cycles);
        return "cycle/list";  // Renvoie à la vue de liste des cycles
    }

    // Affiche les détails d'un cycle spécifique
    @GetMapping("/{id}")
    public String getCycleById(@PathVariable Long id, Model model) {
        CycleDTO cycle = cycleService.getCycleById(id);
        model.addAttribute("cycle", cycle);
        return "cycle/view";  // Renvoie à la vue d'affichage des détails d'un cycle
    }

    // Affiche le formulaire de création d'un nouveau cycle
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("cycle", new CycleDTO());
        return "cycle/create";  // Renvoie au formulaire de création d'un nouveau cycle
    }

    // Traite la création d'un nouveau cycle
    @PostMapping("/new")
    public String createCycle(@ModelAttribute("cycle") CycleDTO cycleDTO) {
        cycleService.createCycle(cycleDTO);
        return "redirect:/cycles";  // Redirige vers la liste des cycles après la création
    }

    // Affiche le formulaire d'édition d'un cycle existant
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        CycleDTO cycle = cycleService.getCycleById(id);
        model.addAttribute("cycle", cycle);
        return "cycle/edit";  // Renvoie au formulaire d'édition d'un cycle
    }

    // Traite la mise à jour d'un cycle existant
    @PostMapping("/{id}/edit")
    public String updateCycle(@PathVariable Long id, @ModelAttribute("cycle") CycleDTO cycleDTO) {
        cycleService.updateCycle(id, cycleDTO);
        return "redirect:/cycles";  // Redirige vers la liste des cycles après la mise à jour
    }

    // Affiche le formulaire de confirmation de suppression d'un cycle
    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        CycleDTO cycle = cycleService.getCycleById(id);
        model.addAttribute("cycle", cycle);
        return "cycle/delete";  // Renvoie au formulaire de confirmation de suppression
    }

    // Traite la suppression d'un cycle
    @PostMapping("/{id}/delete")
    public String deleteCycle(@PathVariable Long id) {
        cycleService.deleteCycle(id);
        return "redirect:/cycles";  // Redirige vers la liste des cycles après la suppression
    }
}
