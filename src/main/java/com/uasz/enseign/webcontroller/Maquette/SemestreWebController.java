package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.SemestreDTO;
import com.uasz.enseign.service.Maquette.SemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/semestres")
public class SemestreWebController {

    private final SemestreService semestreService;

    @Autowired
    public SemestreWebController(SemestreService semestreService) {
        this.semestreService = semestreService;
    }

    @GetMapping
    public String getAllSemestres(Model model) {
        List<SemestreDTO> semestres = semestreService.getAllSemestres();
        model.addAttribute("semestres", semestres);
        return "semestre/list"; // Remplacez "semestre/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getSemestreById(@PathVariable Long id, Model model) {
        SemestreDTO semestre = semestreService.getSemestreById(id);
        model.addAttribute("semestre", semestre);
        return "semestre/details"; // Remplacez "semestre/details" par le chemin réel de votre vue de détails
    }

    @GetMapping("/create")
    public String showCreateSemestreForm(Model model) {
        model.addAttribute("semestreDTO", new SemestreDTO());
        return "semestre/create"; // Remplacez "semestre/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createSemestre(@ModelAttribute("semestreDTO") SemestreDTO semestreDTO) {
        semestreService.createSemestre(semestreDTO);
        return "redirect:/semestres"; // Redirige vers la liste des semestres après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditSemestreForm(@PathVariable Long id, Model model) {
        SemestreDTO semestre = semestreService.getSemestreById(id);
        model.addAttribute("semestre", semestre);
        return "semestre/edit"; // Remplacez "semestre/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editSemestre(@PathVariable Long id, @ModelAttribute("semestreDTO") SemestreDTO semestreDTO) {
        semestreService.updateSemestre(id, semestreDTO);
        return "redirect:/semestres"; // Redirige vers la liste des semestres après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteSemestreForm(@PathVariable Long id, Model model) {
        SemestreDTO semestre = semestreService.getSemestreById(id);
        model.addAttribute("semestre", semestre);
        return "semestre/delete"; // Remplacez "semestre/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteSemestre(@PathVariable Long id) {
        semestreService.deleteSemestre(id);
        return "redirect:/semestres"; // Redirige vers la liste des semestres après la suppression
    }

    @GetMapping("/module/{moduleId}")
    public String getSemestresByModuleId(@PathVariable Long moduleId, Model model) {
        List<SemestreDTO> semestres = semestreService.getSemestresByModuleId(moduleId);
        model.addAttribute("semestres", semestres);
        return "semestre/list"; // Remplacez "semestre/list" par le chemin réel de votre vue de liste
    }
}
