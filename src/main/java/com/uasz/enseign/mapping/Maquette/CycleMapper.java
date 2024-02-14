package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.CycleDTO;
import com.uasz.enseign.model.Maquette.Cycle;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class CycleMapper {
  public CycleDTO fromCycle(Cycle cycle) {
        CycleDTO cycleDTO = new CycleDTO();
        BeanUtils.copyProperties(cycle, cycleDTO);
        return cycleDTO;
    }
    public Cycle fromCyclDTO(CycleDTO cycleDTO) {
        Cycle cycle = new Cycle();
        BeanUtils.copyProperties(cycleDTO, cycle);
        return cycle;
    }
}