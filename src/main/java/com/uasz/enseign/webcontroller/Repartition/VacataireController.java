package com.uasz.enseign.webcontroller.Repartition;

import com.uasz.enseign.dto.Repartition.VacataireDTO;
import com.uasz.enseign.service.Repartition.VacataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacataires")
public class VacataireController {

    private final VacataireService vacataireService;

    @Autowired
    public VacataireController(VacataireService vacataireService) {
        this.vacataireService = vacataireService;
    }

    @GetMapping
    public List<VacataireDTO> getAllVacataires() {
        return vacataireService.getAllVacataires();
    }

    @GetMapping("/{id}")
    public VacataireDTO getVacataireById(@PathVariable Long id) {
        return vacataireService.getVacataireById(id);
    }

    @PostMapping
    public VacataireDTO createVacataire(@RequestBody VacataireDTO vacataireDTO) {
        return vacataireService.createVacataire(vacataireDTO);
    }

    @PutMapping("/{id}")
    public VacataireDTO updateVacataire(@PathVariable Long id, @RequestBody VacataireDTO vacataireDTO) {
        return vacataireService.updateVacataire(id, vacataireDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteVacataire(@PathVariable Long id) {
        vacataireService.deleteVacataire(id);
    }

    // Additional methods for handling EnseignantDTO can be added here if necessary
}
