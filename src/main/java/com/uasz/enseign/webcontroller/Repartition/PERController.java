package com.uasz.enseign.webcontroller.Repartition;

import com.uasz.enseign.dto.Repartition.PERDTO;
import com.uasz.enseign.service.Repartition.PERService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/per")
public class PERController {

    private final PERService perService;

    @Autowired
    public PERController(PERService perService) {
        this.perService = perService;
    }

    @GetMapping
    public List<PERDTO> getAllPERs() {
        return perService.getAllPERs();
    }

    @GetMapping("/{id}")
    public PERDTO getPERById(@PathVariable Long id) {
        return perService.getPERById(id);
    }

    @PostMapping
    public PERDTO createPER(@RequestBody PERDTO perDTO) {
        return perService.createPER(perDTO);
    }

    @PutMapping("/{id}")
    public PERDTO updatePER(@PathVariable Long id, @RequestBody PERDTO perDTO) {
        return perService.updatePER(id, perDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePER(@PathVariable Long id) {
        perService.deletePER(id);
    }

    // Additional methods for EnseignantDTO operations if needed
}
