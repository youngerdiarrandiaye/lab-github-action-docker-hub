package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.RoleDto;

import com.uasz.enseign.model.Maquette.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class RoleMapper {
    public RoleDto fromRole(Role role) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }
    public Role fromRoleDTO(RoleDto roleDtO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDtO, role);
        return role;
    }
}



