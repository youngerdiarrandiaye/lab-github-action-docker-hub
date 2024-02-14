package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.ModuleDTO;
import com.uasz.enseign.dto.Maquette.UEDTO;
import com.uasz.enseign.model.Maquette.EC;
import com.uasz.enseign.service.Maquette.UEService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ues")
public class UEWebController {

    private final UEService ueService;

    @Autowired
    public UEWebController(UEService ueService) {
        this.ueService = ueService;
    }

    @GetMapping("/lists")
    public String showAllUEs(Model model,
                             @RequestParam(name = "page",defaultValue = "0") int p,
                             @RequestParam(name = "size",defaultValue = "3")int s,
                             @RequestParam(name = "keyword",defaultValue = "")String kw) {

        Page<UEDTO>ues=ueService.afficherToutesUEs(kw, PageRequest.of(p,s));
        model.addAttribute("ues",ues.getContent());
        model.addAttribute("pages",new int[ues.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",kw);

        return "ue/list-ue"; // Remplacez "ue/list" par le chemin réel de votre vue
    }

    @GetMapping("/{id}")
    public String showUEDetails(@PathVariable Long id, Model model) {
        UEDTO ueDTO = ueService.afficherUE(id);
        model.addAttribute("ue", ueDTO);
        return "ue/details"; // Remplacez "ue/details" par le chemin réel de votre vue
    }


    @GetMapping("/create")
    public String showCreateUEForm(Model model) {
        model.addAttribute("ueDTO", new UEDTO());
        return "ue/create"; // Remplacez "ue/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createUE(@Valid UEDTO ueDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "ue/create";
        }
        ueService.saveUE(ueDTO);
        return "redirect:/ues/lists";
        // Redirige vers la liste des UE après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditUEForm(@PathVariable Long id, Model model) {
        UEDTO ueDTO = ueService.afficherUE(id);
        model.addAttribute("ueDTO", ueDTO);
        return "ue/edit"; // Remplacez "ue/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editUE(@PathVariable Long id, @ModelAttribute("ueDTO") UEDTO ueDTO) {
        ueDTO.setId(id);
        ueService.modifierUE(ueDTO);
        return "redirect:/ues"; // Redirige vers la liste des UE après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteUEForm(@PathVariable Long id, Model model) {
        UEDTO ueDTO = ueService.afficherUE(id);
        model.addAttribute("ueDTO", ueDTO);
        return "ue/delete"; // Remplacez "ue/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/delete")
    public String deleteUE(@RequestParam(
            name = "id") Long id,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword,
                           @RequestParam(name = "page",defaultValue = "0") int page) {
        ueService.supprimerUE(id);
        return "redirect:/ues/lists?page="+page+"&keyword="+keyword; // Redirige vers la liste des UE après la suppression
    }
}



