
package com.uasz.enseign.webcontroller.Maquette;
import com.uasz.enseign.dto.Maquette.ClasseDTO;
import com.uasz.enseign.service.Maquette.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/ResponsablePedagogique")
public class ClasseWebController {
    private static final String RETOUR_LIST_CLASSES = "/ResponsablePedagogique";
    private final ClasseService classeService;
    @Autowired
    public ClasseWebController(ClasseService classeService) {
        this.classeService = classeService;

    }
    @GetMapping("/list-classes")
    public String showAllUEs(Model model,
                             @RequestParam(name = "page",defaultValue = "0") int p,
                             @RequestParam(name = "size",defaultValue = "3")int s,
                             @RequestParam(name = "keyword",defaultValue = "")String kw) {

        Page<ClasseDTO> classes=classeService.getAllClasses(kw, PageRequest.of(p,s));
        model.addAttribute("classes",classes.getContent());
        model.addAttribute("pages",new int[classes.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",kw);

        return "/classe/list-classes"; // Remplacez "ue/list" par le chemin réel de votre vue
    }
    @PostMapping("/create-classe")
    public String createClasse(@ModelAttribute ClasseDTO classeDTO, Principal principal, Model model, BindingResult result) {
        classeService.createClasse(classeDTO);
        return "redirect:RETOUR_LIST_CLASSES/list-classes";
    }

    @GetMapping("/delete-classe/{id}")
    public String deleteClasse(@PathVariable("id") Long id) {
        classeService.deleteClasse(id);
        return "redirect:RETOUR_LIST_CLASSES/list-classes";
    }

    @GetMapping("/update-classe/{id}")
    @ResponseBody
    public ClasseDTO updateClasse(@PathVariable("id") Long id) {

        return classeService.getClasseById(id);
    }

    @PostMapping("/edit-classe")
    public String editClasse(@ModelAttribute ClasseDTO classeDTO) {
        classeService.updateClasse(classeDTO);
        return "redirect:RETOUR_LIST_CLASSES/list-classes";
    }
}

