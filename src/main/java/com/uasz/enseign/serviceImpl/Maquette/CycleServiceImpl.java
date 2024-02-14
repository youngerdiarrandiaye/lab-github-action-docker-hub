package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.CycleDTO;
import com.uasz.enseign.mapping.Maquette.CycleMapper;
import com.uasz.enseign.model.Maquette.Cycle;
import com.uasz.enseign.repository.Maquette.CycleRepository;
import com.uasz.enseign.service.Maquette.CycleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CycleServiceImpl implements CycleService {

    private final CycleRepository cycleRepository;
    private final CycleMapper cycleMapper;

    @Autowired
    public CycleServiceImpl(CycleRepository cycleRepository, CycleMapper cycleMapper) {
        this.cycleRepository = cycleRepository;
        this.cycleMapper = cycleMapper;
    }

    @Override
    public List<CycleDTO> getAllCycles() {
        List<Cycle> cycles = cycleRepository.findAll();
        return cycles.stream()
                .map(cycleMapper::fromCycle)
                .collect(Collectors.toList());
    }

    @Override
    public CycleDTO getCycleById(Long id) {
        Cycle cycle = cycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cycle not found with id: " + id));
        return cycleMapper.fromCycle(cycle);
    }

    @Override
    public CycleDTO createCycle(CycleDTO cycleDTO) {
        Cycle cycle = cycleMapper.fromCyclDTO(cycleDTO);
        Cycle savedCycle = cycleRepository.save(cycle);
        return cycleMapper.fromCycle(savedCycle);
    }

    @Override
    public CycleDTO updateCycle(Long id, CycleDTO cycleDTO) {
        Cycle existingCycle = cycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cycle not found with id: " + id));

        // Update properties of existingCycle with cycleDTO
        BeanUtils.copyProperties(cycleDTO, existingCycle, "id");

        Cycle updatedCycle = cycleRepository.save(existingCycle);
        return cycleMapper.fromCycle(updatedCycle);
    }

    @Override
    public void deleteCycle(Long id) {
        cycleRepository.deleteById(id);
    }
}
