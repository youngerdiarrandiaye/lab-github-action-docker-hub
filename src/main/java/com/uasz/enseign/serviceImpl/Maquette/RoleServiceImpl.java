package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.RoleDto;
import com.uasz.enseign.mapping.Maquette.RoleMapper;
import com.uasz.enseign.model.Maquette.Role;
import com.uasz.enseign.repository.Maquette.RoleRepository;
import com.uasz.enseign.service.Maquette.RoleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role addRole(RoleDto roleDto) {
        Role role = roleMapper.fromRoleDTO(roleDto);
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleRepository.deleteById(Long.valueOf(roleId));
    }

    @Override
    public List<Role> getAllRole() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public RoleDto getRoleByName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        return roleMapper.fromRole(role);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public void updateRole(Role role) {

    }
}