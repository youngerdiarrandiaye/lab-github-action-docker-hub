package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.ModuleDTO;
import com.uasz.enseign.mapping.Maquette.ModuleMapper;
import com.uasz.enseign.model.Maquette.Module;
import com.uasz.enseign.repository.Maquette.ModuleRepository;
import com.uasz.enseign.service.Maquette.ModuleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional @Slf4j
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    @Autowired
    public ModuleServiceImpl(ModuleRepository moduleRepository, ModuleMapper moduleMapper) {
        this.moduleRepository = moduleRepository;
        this.moduleMapper = moduleMapper;
    }

    @Override
    public List<ModuleDTO> getAllModules() {
        List<Module> modules = moduleRepository.findAll();
        return modules.stream()
                .map(moduleMapper::fromModule)
                .collect(Collectors.toList());
    }

    @Override
    public ModuleDTO getModuleById(Long id) {
        Optional<Module> module = moduleRepository.findById(id);
        return module.map(moduleMapper::fromModule).orElse(null);
    }

    @Override
    public ModuleDTO createModule(ModuleDTO moduleDTO) {
        Module module = moduleMapper.fromModuleDTO(moduleDTO);
        Module savedModule = moduleRepository.save(module);
        return moduleMapper.fromModule(savedModule);
    }

    @Override
    public ModuleDTO updateModule(Long id, ModuleDTO moduleDTO) {
        Optional<Module> existingModuleOptional = moduleRepository.findById(id);
        if (existingModuleOptional.isPresent()) {
            Module existingModule = existingModuleOptional.get();
            // Update fields from DTO
            existingModule.setNom(moduleDTO.getNom());
            // Save and return updated Module
            Module updatedModule = moduleRepository.save(existingModule);
            return moduleMapper.fromModule(updatedModule);
        } else {
            return null; // Module not found
        }
    }

    @Override
    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }
    @Override
    public List<ModuleDTO> getModulesByUeId(Long ueId) {
        List<Module> modules = moduleRepository.findByUeId(ueId);
        return modules.stream()
                .map(moduleMapper::fromModule)
                .collect(Collectors.toList());
    }

    @Override
    public List<ModuleDTO> getModulesByEcId(Long ecId) {
        List<Module> modules = moduleRepository.findByEcId(ecId);
        return modules.stream()
                .map(moduleMapper::fromModule)
                .collect(Collectors.toList());
    }

    @Override
    public List<ModuleDTO> getModulesBySemestreId(Long semestreId) {
        List<Module> modules = moduleRepository.findBySemestreId(semestreId);
        return modules.stream()
                .map(moduleMapper::fromModule)
                .collect(Collectors.toList());
    }
}
