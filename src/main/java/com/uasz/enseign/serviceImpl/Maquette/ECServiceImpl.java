package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.ECDTO;
import com.uasz.enseign.mapping.Maquette.ECMapper;
import com.uasz.enseign.model.Maquette.EC;
import com.uasz.enseign.repository.Maquette.ECRepository;
import com.uasz.enseign.service.Maquette.ECService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class ECServiceImpl implements ECService {

    private final ECRepository ecRepository;
    private final ECMapper ecMapper;

    @Autowired
    public ECServiceImpl(ECRepository ecRepository, ECMapper ecMapper) {
        this.ecRepository = ecRepository;
        this.ecMapper = ecMapper;
    }

    @Override
    public List<ECDTO> getAllECs() {
        List<EC> ecs = ecRepository.findAll();
        return ecs.stream()
                .map(ecMapper::fromEC)
                .collect(Collectors.toList());
    }

    @Override
    public ECDTO getECById(Long id) {
        EC ec = ecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EC not found with id: " + id));
        return ecMapper.fromEC(ec);
    }

    @Override
    public ECDTO createEC(ECDTO ecDTO) {
        EC ec = ecMapper.fromECDTO(ecDTO);
        EC savedEC = ecRepository.save(ec);
        return ecMapper.fromEC(savedEC);
    }

    @Override
    public ECDTO updateEC(Long id, ECDTO ecDTO) {
        EC existingEC = ecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EC not found with id: " + id));

        // Update properties of existingEC with ecDTO
        BeanUtils.copyProperties(ecDTO, existingEC, "id");

        EC updatedEC = ecRepository.save(existingEC);
        return ecMapper.fromEC(updatedEC);
    }

    @Override
    public void deleteEC(Long id) {
        ecRepository.deleteById(id);
    }

    @Override
    public List<ECDTO> getECsByUeId(Long ueId) {
        List<EC> ecsByUeId = ecRepository.findByUeId(ueId);
        return ecsByUeId.stream()
                .map(ecMapper::fromEC)
                .collect(Collectors.toList());
    }

    @Override
    public List<ECDTO> getECsByModuleId(Long moduleId) {
        List<EC> ecsByModuleId = ecRepository.findByModuleId(moduleId);
        return ecsByModuleId.stream()
                .map(ecMapper::fromEC)
                .collect(Collectors.toList());
    }

    @Override
    public List<ECDTO> getECsBySemestreId(Long semestreId) {
        List<EC> ecsBySemestreId = ecRepository.findBySemestre_Id(semestreId);
        return ecsBySemestreId.stream()
                .map(ecMapper::fromEC)
                .collect(Collectors.toList());
    }
}
