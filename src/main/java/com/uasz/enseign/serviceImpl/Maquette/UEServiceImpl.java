package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.ModuleDTO;
import com.uasz.enseign.dto.Maquette.UEDTO;
import com.uasz.enseign.mapping.Maquette.UEMapper;
import com.uasz.enseign.model.Maquette.EC;
import com.uasz.enseign.model.Maquette.UE;
import com.uasz.enseign.repository.Maquette.UERepository;
import com.uasz.enseign.service.Maquette.UEService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UEServiceImpl implements UEService {

    private final UERepository ueRepository;
    private final UEMapper ueMapper;

    @Autowired
    public UEServiceImpl(UERepository ueRepository, UEMapper ueMapper) {
        this.ueRepository = ueRepository;
        this.ueMapper = ueMapper;
    }
    @Override
    public UEDTO saveUE(UEDTO ueDTO) {
        UE ue = ueMapper.fromUEDTO(ueDTO);
        ue = ueRepository.save(ue);
        return ueMapper.fromUE(ue);
    }

    @Override
    public Page<UEDTO> afficherToutesUEs(String keyword, Pageable pageable) {
        Page<UE> ues = ueRepository.findByCodeContains(keyword,pageable);
        return ues.map(ueMapper::fromUE);
    }

    @Override
    public UEDTO afficherUE(Long idUE) {
        Optional<UE> optionalUE = ueRepository.findById(idUE);
        return optionalUE.map(ueMapper::fromUE).orElse(null);
    }

    @Override
    public UEDTO modifierUE(UEDTO ueDTO) {
        Optional<UE> optionalUE = ueRepository.findById(ueDTO.getId());
        if (optionalUE.isPresent()) {
            UE ue = ueMapper.fromUEDTO(ueDTO);
            ue = ueRepository.save(ue);
            return ueMapper.fromUE(ue);
        }
        return null;
    }

    @Override
    public void supprimerUE(Long idUE) {
        ueRepository.deleteById(idUE);
    }

    @Override
    public List<EC> trouverEcsParUE(Long idUE) {
        Optional<UE> optionalUE = ueRepository.findById(idUE);
        return optionalUE.map(UE::getEcs).orElse(null);
    }
//
//    @Override
//    public List<ModuleDTO> trouverModulesParUE(Long idUE) {
//        Optional<UE> optionalUE = ueRepository.findById(idUE);
//        return optionalUE.map(ue -> ue.getModules().stream()
//                        .map(module -> {
//                            ModuleDTO moduleDTO = new ModuleDTO();
//                            BeanUtils.copyProperties(module, moduleDTO);
//                            return moduleDTO;
//                        })
//                        .collect(Collectors.toList()))
//                .orElse(null);
//    }
}
