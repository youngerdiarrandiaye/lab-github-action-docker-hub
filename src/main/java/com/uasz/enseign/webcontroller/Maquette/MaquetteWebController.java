package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.MaquetteDTO;
import com.uasz.enseign.service.Maquette.MaquetteService;
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
@RequestMapping("/maquettes")
public class MaquetteWebController {

    private final MaquetteService maquetteService;

    @Autowired
    public MaquetteWebController(MaquetteService maquetteService) {
        this.maquetteService = maquetteService;
    }

    @GetMapping
    public String getAllMaquettes(Model model) {
        List<MaquetteDTO> maquettes = maquetteService.getAllMaquettes();
        model.addAttribute("maquettes", maquettes);
        return "maquette/list"; // Remplacez "maquette/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getMaquetteById(@PathVariable Long id, Model model) {
        MaquetteDTO maquetteDTO = maquetteService.getMaquetteById(id);
        if (maquetteDTO != null) {
            model.addAttribute("maquette", maquetteDTO);
            return "maquette/details"; // Remplacez "maquette/details" par le chemin réel de votre vue de détails
        } else {
            return "maquette/notFound"; // Remplacez "maquette/notFound" par le chemin réel de votre vue pour les ressources non trouvées
        }
    }

    @GetMapping("/create")
    public String showCreateMaquetteForm(Model model) {
        model.addAttribute("maquetteDTO", new MaquetteDTO());
        return "maquette/create"; // Remplacez "maquette/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createMaquette(@ModelAttribute("maquetteDTO") MaquetteDTO maquetteDTO) {
        maquetteService.createMaquette(maquetteDTO);
        return "redirect:/maquettes"; // Redirige vers la liste des maquettes après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditMaquetteForm(@PathVariable Long id, Model model) {
        MaquetteDTO maquetteDTO = maquetteService.getMaquetteById(id);
        model.addAttribute("maquetteDTO", maquetteDTO);
        return "maquette/edit"; // Remplacez "maquette/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editMaquette(@PathVariable Long id, @ModelAttribute("maquetteDTO") MaquetteDTO maquetteDTO) {
        maquetteService.updateMaquette(id, maquetteDTO);
        return "redirect:/maquettes"; // Redirige vers la liste des maquettes après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteMaquetteForm(@PathVariable Long id, Model model) {
        MaquetteDTO maquetteDTO = maquetteService.getMaquetteById(id);
        model.addAttribute("maquetteDTO", maquetteDTO);
        return "maquette/delete"; // Remplacez "maquette/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteMaquette(@PathVariable Long id) {
        maquetteService.deleteMaquette(id);
        return "redirect:/maquettes"; // Redirige vers la liste des maquettes après la suppression
    }

    @GetMapping("/formation/{formationId}")
    public String getMaquettesByFormationId(@PathVariable Long formationId, Model model) {
        List<MaquetteDTO> maquettes = maquetteService.getMaquettesByFormationId(formationId);
        model.addAttribute("maquettes", maquettes);
        return "maquette/list"; // Remplacez "maquette/list" par le chemin réel de votre vue de liste
    }
}
