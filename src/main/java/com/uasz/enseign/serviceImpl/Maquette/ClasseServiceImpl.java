package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.ClasseDTO;
import com.uasz.enseign.mapping.Maquette.ClasseMapper;
import com.uasz.enseign.model.Maquette.Classe;
import com.uasz.enseign.repository.Maquette.ClasseRepository;
import com.uasz.enseign.service.Maquette.ClasseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;
    private final ClasseMapper classeMapper;
    @Autowired
    public ClasseServiceImpl(ClasseRepository classeRepository, ClasseMapper classeMapper) {
        this.classeRepository = classeRepository;
        this.classeMapper = classeMapper;
    }

    @Override
    public void createClasse(ClasseDTO classeDTO) {
        System.out.println("Input classe: " + classeDTO.toString());
        Classe classe = new Classe();
        classe.setLibelle(classeDTO.getLibelle());
        classe.setEffectif(classeDTO.getEffectif());
        classe.setNbreGroupe(classeDTO.getNbreGroupe());
        classe.setDescription(classeDTO.getDescription());
        classe.setEnseignement(classeDTO.getEnseignement());
        classe.setSemestre(classeDTO.getSemestre());
        classe.setGroupes(classeDTO.getGroupes());
        classe.setFormation(classeDTO.getFormation());

        classeRepository.save(classe);
    }
    @Override
    public void deleteClasse(Long id) {
        classeRepository.deleteById(id);
    }


    @Override
    public Page<ClasseDTO> getAllClasses(String keyword, Pageable pageable) {
        Page<Classe> classes = classeRepository.findByLibelleContains(keyword,pageable);
        return classes.map(classeMapper::fromClasse);
    }
    @Override
    public void updateClasse(ClasseDTO classeDTO) {
        // Vérification si la classe existe avant la mise à jour
        Classe existingClasse = classeRepository.findById(classeDTO.getId())
                .orElseThrow(() -> new RuntimeException("La classe n'existe pas"));

        // Mise à jour des attributs nécessaires
        existingClasse.setLibelle(classeDTO.getLibelle());
        existingClasse.setEffectif(classeDTO.getEffectif());
        existingClasse.setNbreGroupe(classeDTO.getNbreGroupe());
        existingClasse.setDescription(classeDTO.getDescription());
        existingClasse.setEnseignement(classeDTO.getEnseignement());
        existingClasse.setSemestre(classeDTO.getSemestre());
        existingClasse.setGroupes(classeDTO.getGroupes());
        existingClasse.setFormation(classeDTO.getFormation());

        // Enregistrement de la mise à jour dans la base de données
        classeRepository.save(existingClasse);
    }

    @Override
    public ClasseDTO getClasseById(Long classeId) {
        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("La classe n'existe pas"));
        return classeMapper.fromClasse(classe);
    }
}
