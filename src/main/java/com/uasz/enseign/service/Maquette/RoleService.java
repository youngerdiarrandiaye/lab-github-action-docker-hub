package com.uasz.enseign.service.Maquette;

import com.uasz.enseign.dto.Maquette.RoleDto;
import com.uasz.enseign.model.Maquette.Role;

import java.util.List;

public interface RoleService {
    Role addRole(RoleDto role);
    void deleteRole(Integer integer);
    List<Role> getAllRole();
    RoleDto getRoleByName(String roleName);
    Role getRoleById(Long roleId);

    List<Role> getAllRoles();

    void updateRole(Role role);
}
