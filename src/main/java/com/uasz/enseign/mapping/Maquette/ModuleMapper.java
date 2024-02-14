package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.ModuleDTO;
import com.uasz.enseign.model.Maquette.Module;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class ModuleMapper {
    public ModuleDTO fromModule(Module module) {
        ModuleDTO moduleDTO = new ModuleDTO();
        BeanUtils.copyProperties(module, moduleDTO);
        return moduleDTO;
    }
    public Module fromModuleDTO(ModuleDTO moduleDTO) {
        Module module = new Module();
        BeanUtils.copyProperties(moduleDTO, module);
        return module;
    }
}



