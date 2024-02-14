package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.ModuleDTO;
import com.uasz.enseign.service.Maquette.ModuleService;
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
@RequestMapping("/modules")
public class ModuleWebController {

    private final ModuleService moduleService;

    @Autowired
    public ModuleWebController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping
    public String getAllModules(Model model) {
        List<ModuleDTO> modules = moduleService.getAllModules();
        model.addAttribute("modules", modules);
        return "module/list"; // Remplacez "module/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getModuleById(@PathVariable Long id, Model model) {
        ModuleDTO moduleDTO = moduleService.getModuleById(id);
        if (moduleDTO != null) {
            model.addAttribute("module", moduleDTO);
            return "module/details"; // Remplacez "module/details" par le chemin réel de votre vue de détails
        } else {
            return "module/notFound"; // Remplacez "module/notFound" par le chemin réel de votre vue pour les ressources non trouvées
        }
    }

    @GetMapping("/create")
    public String showCreateModuleForm(Model model) {
        model.addAttribute("moduleDTO", new ModuleDTO());
        return "module/create"; // Remplacez "module/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createModule(@ModelAttribute("moduleDTO") ModuleDTO moduleDTO) {
        moduleService.createModule(moduleDTO);
        return "redirect:/modules"; // Redirige vers la liste des modules après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditModuleForm(@PathVariable Long id, Model model) {
        ModuleDTO moduleDTO = moduleService.getModuleById(id);
        model.addAttribute("moduleDTO", moduleDTO);
        return "module/edit"; // Remplacez "module/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editModule(@PathVariable Long id, @ModelAttribute("moduleDTO") ModuleDTO moduleDTO) {
        moduleService.updateModule(id, moduleDTO);
        return "redirect:/modules"; // Redirige vers la liste des modules après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteModuleForm(@PathVariable Long id, Model model) {
        ModuleDTO moduleDTO = moduleService.getModuleById(id);
        model.addAttribute("moduleDTO", moduleDTO);
        return "module/delete"; // Remplacez "module/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return "redirect:/modules"; // Redirige vers la liste des modules après la suppression
    }

    @GetMapping("/ue/{ueId}")
    public String getModulesByUeId(@PathVariable Long ueId, Model model) {
        List<ModuleDTO> modules = moduleService.getModulesByUeId(ueId);
        model.addAttribute("modules", modules);
        return "module/list"; // Remplacez "module/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/ec/{ecId}")
    public String getModulesByEcId(@PathVariable Long ecId, Model model) {
        List<ModuleDTO> modules = moduleService.getModulesByEcId(ecId);
        model.addAttribute("modules", modules);
        return "module/list"; // Remplacez "module/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/semestre/{semestreId}")
    public String getModulesBySemestreId(@PathVariable Long semestreId, Model model) {
        List<ModuleDTO> modules = moduleService.getModulesBySemestreId(semestreId);
        model.addAttribute("modules", modules);
        return "module/list"; // Remplacez "module/list" par le chemin réel de votre vue de liste
    }
}
