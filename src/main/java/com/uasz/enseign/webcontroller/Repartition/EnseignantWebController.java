package com.uasz.enseign.webcontroller.Repartition;

import com.uasz.enseign.dto.Repartition.EnseignantDTO;
import com.uasz.enseign.service.Repartition.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enseignants")
public class EnseignantWebController {

    private final EnseignantService enseignantService;

    @Autowired
    public EnseignantWebController(@Qualifier("enseignantServiceImpl") EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @GetMapping
    public String getAllEnseignants(Model model) {
        List<EnseignantDTO> enseignants = enseignantService.getAllEnseignants();
        model.addAttribute("enseignants", enseignants);
        return "enseignant/list"; // Assurez-vous d'avoir la page "list" dans le dossier des vues
    }

    @GetMapping("/{id}")
    public String getEnseignantById(@PathVariable Long id, Model model) {
        EnseignantDTO enseignant = enseignantService.getEnseignantById(id);
        model.addAttribute("enseignant", enseignant);
        return "enseignant/details"; // Assurez-vous d'avoir la page "details" dans le dossier des vues
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("enseignant", new EnseignantDTO());
        return "enseignant/create"; // Assurez-vous d'avoir la page "create" dans le dossier des vues
    }

    @PostMapping("/create")
    public String createEnseignant(@ModelAttribute EnseignantDTO enseignantDTO) {
        enseignantService.createEnseignant(enseignantDTO);
        return "redirect:/enseignants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        EnseignantDTO enseignant = enseignantService.getEnseignantById(id);
        model.addAttribute("enseignant", enseignant);
        return "enseignant/edit"; // Assurez-vous d'avoir la page "edit" dans le dossier des vues
    }

    @PostMapping("/edit/{id}")
    public String updateEnseignant(@PathVariable Long id, @ModelAttribute EnseignantDTO enseignantDTO) {
        enseignantService.updateEnseignant(id, enseignantDTO);
        return "redirect:/enseignants";
    }

    @GetMapping("/delete/{id}")
    public String deleteEnseignant(@PathVariable Long id) {
        enseignantService.deleteEnseignant(id);
        return "redirect:/enseignants";
    }
}
