package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.SemestreDTO;
import com.uasz.enseign.mapping.Maquette.SemestreMapper;
import com.uasz.enseign.model.Maquette.Classe;
import com.uasz.enseign.model.Maquette.Semestre;
import com.uasz.enseign.repository.Maquette.SemestreRepository;
import com.uasz.enseign.service.Maquette.SemestreService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SemestreServiceImpl implements SemestreService {

    private final SemestreRepository semestreRepository;
    private final SemestreMapper semestreMapper;

    @Autowired
    public SemestreServiceImpl(SemestreRepository semestreRepository, SemestreMapper semestreMapper) {
        this.semestreRepository = semestreRepository;
        this.semestreMapper = semestreMapper;
    }

    @Override
    public List<SemestreDTO> getAllSemestres() {
        List<Semestre> semestres = semestreRepository.findAll();
        return semestres.stream()
                .map(semestreMapper::fromSemestre)
                .collect(Collectors.toList());
    }

    @Override
    public SemestreDTO getSemestreById(Long id) {
        Semestre semestre = semestreRepository.findById(id).get();
        return (semestre != null) ? semestreMapper.fromSemestre(semestre) : null;
    }

    @Override
    public SemestreDTO createSemestre(SemestreDTO semestreDTO) {
        Semestre semestre = semestreMapper.fromSemestreDTO(semestreDTO);
        semestre = semestreRepository.save(semestre);
        return semestreMapper.fromSemestre(semestre);
    }

    @Override
    public SemestreDTO updateSemestre(Long id, SemestreDTO semestreDTO) {
        Semestre existingSemestre = semestreRepository.findById(id).orElse(null);
        if (existingSemestre != null) {
            Semestre updatedSemestre = semestreMapper.fromSemestreDTO(semestreDTO);
            updatedSemestre.setId(existingSemestre.getId());
            updatedSemestre = semestreRepository.save(updatedSemestre);
            return semestreMapper.fromSemestre(updatedSemestre);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSemestre(Long id) {
        semestreRepository.deleteById(id);
    }
//    @Override
//    public List<SemestreDTO> getSemestresByClasseId(List<Classe> classes) {
//        // Vérifiez cette ligne
//        List<Semestre> semestres = semestreRepository.findByClasses(classes);
//        return semestres.stream()
//                .map(semestreMapper::fromSemestre)
//                .collect(Collectors.toList());
//    }


    @Override
    public List<SemestreDTO> getSemestresByModuleId(Long moduleId) {
        List<Semestre> semestres = semestreRepository.findByModulesId(moduleId);
        return semestres.stream()
                .map(semestreMapper::fromSemestre)
                .collect(Collectors.toList());
    }
}
