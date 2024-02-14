package com.uasz.enseign.serviceImpl.Repartition;

import com.uasz.enseign.dto.Repartition.RepartitionDTO;
import com.uasz.enseign.mapping.Repartition.RepartitionMapper;
import com.uasz.enseign.model.Repartition.Repartition;
import com.uasz.enseign.repository.Repartition.RepartitionRepository;
import com.uasz.enseign.service.Repartition.RepartitionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RepartitionServiceImpl implements RepartitionService {

    private final RepartitionRepository repartitionRepository;
    private final RepartitionMapper repartitionMapper;

    @Autowired
    public RepartitionServiceImpl(RepartitionRepository repartitionRepository, RepartitionMapper repartitionMapper) {
        this.repartitionRepository = repartitionRepository;
        this.repartitionMapper = repartitionMapper;
    }

    @Override
    public List<RepartitionDTO> getAllRepartitions() {
        List<RepartitionDTO> repartitionDTOs = repartitionRepository.findAll().stream()
                .map(repartitionMapper::fromRepartition)
                .collect(Collectors.toList());
        return repartitionDTOs;
    }

    @Override
    public RepartitionDTO getRepartitionById(Long id) {
        Optional<Repartition> repartitionOptional = repartitionRepository.findById(id);
        return repartitionOptional.map(repartitionMapper::fromRepartition).orElse(null);
    }

    @Override
    public RepartitionDTO createRepartition(RepartitionDTO repartitionDTO) {
        Repartition repartition = repartitionMapper.fromRepartitionDTO(repartitionDTO);
        Repartition savedRepartition = repartitionRepository.save(repartition);
        return repartitionMapper.fromRepartition(savedRepartition);
    }

    @Override
    public RepartitionDTO updateRepartition(Long id, RepartitionDTO repartitionDTO) {
        Optional<Repartition> existingRepartitionOptional = repartitionRepository.findById(id);
        if (existingRepartitionOptional.isPresent()) {
            Repartition existingRepartition = existingRepartitionOptional.get();
            // Update fields from DTO
            // Example: existingRepartition.setSomeField(repartitionDTO.getSomeField());
            Repartition updatedRepartition = repartitionRepository.save(existingRepartition);
            return repartitionMapper.fromRepartition(updatedRepartition);
        } else {
            return null; // Repartition not found
        }
    }

    @Override
    public void deleteRepartition(Long id) {
        repartitionRepository.deleteById(id);
    }
}
