package com.uasz.enseign.webcontroller.EmploiDuTemps;

import com.uasz.enseign.dto.EmploiDuTemps.SeanceDTO;
import com.uasz.enseign.service.EmploiDuTemps.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seances")
public class SeanceWebController {

    private final SeanceService seanceService;

    @Autowired
    public SeanceWebController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping
    public String getAllSeances(Model model) {
        List<SeanceDTO> seances = seanceService.getAllSeances();
        model.addAttribute("seances", seances);
        return "seance/list"; // Remplacez "seance/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getSeanceById(@PathVariable Long id, Model model) {
        SeanceDTO seance = seanceService.getSeanceById(id);
        model.addAttribute("seance", seance);
        return "seance/details"; // Remplacez "seance/details" par le chemin réel de votre vue de détails
    }

    @GetMapping("/create")
    public String showCreateSeanceForm(Model model) {
        model.addAttribute("seanceDTO", new SeanceDTO());
        return "seance/create"; // Remplacez "seance/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createSeance(@ModelAttribute("seanceDTO") SeanceDTO seanceDTO) {
        seanceService.createSeance(seanceDTO);
        return "redirect:/seances"; // Redirige vers la liste des séances après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditSeanceForm(@PathVariable Long id, Model model) {
        SeanceDTO seance = seanceService.getSeanceById(id);
        model.addAttribute("seance", seance);
        return "seance/edit"; // Remplacez "seance/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editSeance(@PathVariable Long id, @ModelAttribute("seanceDTO") SeanceDTO seanceDTO) {
        seanceService.updateSeance(id, seanceDTO);
        return "redirect:/seances"; // Redirige vers la liste des séances après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteSeanceForm(@PathVariable Long id, Model model) {
        SeanceDTO seance = seanceService.getSeanceById(id);
        model.addAttribute("seance", seance);
        return "seance/delete"; // Remplacez "seance/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteSeance(@PathVariable Long id) {
        seanceService.deleteSeance(id);
        return "redirect:/seances"; // Redirige vers la liste des séances après la suppression
    }
}
