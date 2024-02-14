package com.uasz.enseign.serviceImpl.Repartition;

import com.uasz.enseign.dto.Repartition.EnseignantDTO;
import com.uasz.enseign.dto.Repartition.PERDTO;
import com.uasz.enseign.model.Repartition.PER;
import com.uasz.enseign.repository.Repartition.PERRepository;
import com.uasz.enseign.service.Repartition.PERService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uasz.enseign.mapping.Repartition.PerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PERServiceImpl implements PERService {

    private final PERRepository perRepository;
    private final PerMapper perMapper;

    @Autowired
    public PERServiceImpl(PERRepository perRepository, PerMapper perMapper) {
        this.perRepository = perRepository;
        this.perMapper = perMapper;
    }

    @Override
    public List<PERDTO> getAllPERs() {
        List<PER> pers = perRepository.findAll();
        return pers.stream()
                .map(perMapper::fromPER)
                .collect(Collectors.toList());
    }

    @Override
    public PERDTO getPERById(Long id) {
        PER per = perRepository.findById(id).orElse(null);
        return (per != null) ? perMapper.fromPER(per) : null;
    }

    @Override
    public PERDTO createPER(PERDTO perDTO) {
        PER per = perMapper.fromPERDTO(perDTO);
        per = perRepository.save(per);
        return perMapper.fromPER(per);
    }

    @Override
    public PERDTO updatePER(Long id, PERDTO perDTO) {
        PER existingPER = perRepository.findById(id).orElse(null);
        if (existingPER != null) {
            PER updatedPER = perMapper.fromPERDTO(perDTO);
            updatedPER.setId(existingPER.getId());
            updatedPER = perRepository.save(updatedPER);
            return perMapper.fromPER(updatedPER);
        } else {
            return null;
        }
    }

    @Override
    public void deletePER(Long id) {
        perRepository.deleteById(id);
    }

    @Override
    public List<EnseignantDTO> getAllEnseignants() {
        return null;
    }

    @Override
    public EnseignantDTO getEnseignantById(Long id) {
        return null;
    }

    @Override
    public EnseignantDTO createEnseignant(EnseignantDTO enseignantDTO) {
        return null;
    }

    @Override
    public EnseignantDTO updateEnseignant(Long id, EnseignantDTO enseignantDTO) {
        return null;
    }

    @Override
    public void deleteEnseignant(Long id) {

    }
}
