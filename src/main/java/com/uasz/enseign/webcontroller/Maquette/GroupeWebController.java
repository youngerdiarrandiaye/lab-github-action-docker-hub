package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.GroupeDTO;
import com.uasz.enseign.service.Maquette.GroupeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Slf4j
@Controller
@RequestMapping("/groupes")
public class GroupeWebController {

    private final GroupeService groupeService;

    @Autowired
    public GroupeWebController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @GetMapping
    public String getAllGroupes(Model model) {
        List<GroupeDTO> groupes = groupeService.getAllGroupes();
        model.addAttribute("groupes", groupes);
        return "groupe/list"; // Remplacez "groupe/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getGroupeById(@PathVariable Long id, Model model) {
        GroupeDTO groupeDTO = groupeService.getGroupeById(id);
        if (groupeDTO != null) {
            model.addAttribute("groupe", groupeDTO);
            return "groupe/details"; // Remplacez "groupe/details" par le chemin réel de votre vue de détails
        } else {
            return "groupe/notFound"; // Remplacez "groupe/notFound" par le chemin réel de votre vue pour les ressources non trouvées
        }
    }

    @GetMapping("/create")
    public String showCreateGroupeForm(Model model) {
        model.addAttribute("groupeDTO", new GroupeDTO());
        return "groupe/create"; // Remplacez "groupe/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createGroupe(@ModelAttribute("groupeDTO") GroupeDTO groupeDTO) {
        groupeService.createGroupe(groupeDTO);
        return "redirect:/groupes"; // Redirige vers la liste des groupes après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditGroupeForm(@PathVariable Long id, Model model) {
        GroupeDTO groupeDTO = groupeService.getGroupeById(id);
        model.addAttribute("groupeDTO", groupeDTO);
        return "groupe/edit"; // Remplacez "groupe/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editGroupe(@PathVariable Long id, @ModelAttribute("groupeDTO") GroupeDTO groupeDTO) {
        groupeService.updateGroupe(id, groupeDTO);
        return "redirect:/groupes"; // Redirige vers la liste des groupes après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteGroupeForm(@PathVariable Long id, Model model) {
        GroupeDTO groupeDTO = groupeService.getGroupeById(id);
        model.addAttribute("groupeDTO", groupeDTO);
        return "groupe/delete"; // Remplacez "groupe/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteGroupe(@PathVariable Long id) {
        groupeService.deleteGroupe(id);
        return "redirect:/groupes"; // Redirige vers la liste des groupes après la suppression
    }

    @GetMapping("/classe/{classeId}")
    public String getGroupesByClasseId(@PathVariable Long classeId, Model model) {
        List<GroupeDTO> groupes = groupeService.getGroupesByClasseId(classeId);
        model.addAttribute("groupes", groupes);
        return "groupe/list"; // Remplacez "groupe/list" par le chemin réel de votre vue de liste
    }
}
