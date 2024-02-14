package com.uasz.enseign.webcontroller.Maquette;

import com.uasz.enseign.dto.Maquette.RoleDto;
import com.uasz.enseign.model.Maquette.Role;
import com.uasz.enseign.service.Maquette.RoleService;
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
@RequestMapping("/roles")
public class RoleWebController {

    private final RoleService roleService;

    @Autowired
    public RoleWebController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "role/list"; // Remplacez "role/list" par le chemin réel de votre vue de liste
    }

    @GetMapping("/{id}")
    public String getRoleById(@PathVariable Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "role/details"; // Remplacez "role/details" par le chemin réel de votre vue de détails
    }

    @GetMapping("/create")
    public String showCreateRoleForm(Model model) {
        model.addAttribute("roleDto", new RoleDto());
        return "role/create"; // Remplacez "role/create" par le chemin réel de votre vue de création
    }

    @PostMapping("/create")
    public String createRole(@ModelAttribute("roleDto") RoleDto roleDto) {
        roleService.addRole(roleDto);
        return "redirect:/roles"; // Redirige vers la liste des rôles après la création
    }

    @GetMapping("/{id}/edit")
    public String showEditRoleForm(@PathVariable Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "role/edit"; // Remplacez "role/edit" par le chemin réel de votre vue d'édition
    }

    @PostMapping("/{id}/edit")
    public String editRole(@PathVariable Long id, @ModelAttribute("roleDto") RoleDto roleDto) {
        // Logique pour mettre à jour le rôle
        return "redirect:/roles"; // Redirige vers la liste des rôles après la modification
    }

    @GetMapping("/{id}/delete")
    public String showDeleteRoleForm(@PathVariable Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "role/delete"; // Remplacez "role/delete" par le chemin réel de votre vue de suppression
    }

    @PostMapping("/{id}/delete")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id.intValue());
        return "redirect:/roles"; // Redirige vers la liste des rôles après la suppression
    }

    @GetMapping("/name/{roleName}")
    public String getRoleByName(@PathVariable String roleName, Model model) {
        RoleDto roleDto = roleService.getRoleByName(roleName);
        model.addAttribute("roleDto", roleDto);
        return "role/details"; // Remplacez "role/details" par le chemin réel de votre vue de détails
    }
}
