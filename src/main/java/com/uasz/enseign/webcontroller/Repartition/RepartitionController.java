package com.uasz.enseign.webcontroller.Repartition;

import com.uasz.enseign.dto.Repartition.RepartitionDTO;
import com.uasz.enseign.service.Repartition.RepartitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repartitions")
public class RepartitionController {

    private final RepartitionService repartitionService;

    @Autowired
    public RepartitionController(RepartitionService repartitionService) {
        this.repartitionService = repartitionService;
    }

    @GetMapping
    public List<RepartitionDTO> getAllRepartitions() {
        return repartitionService.getAllRepartitions();
    }

    @GetMapping("/{id}")
    public RepartitionDTO getRepartitionById(@PathVariable Long id) {
        return repartitionService.getRepartitionById(id);
    }

    @PostMapping
    public RepartitionDTO createRepartition(@RequestBody RepartitionDTO repartitionDTO) {
        return repartitionService.createRepartition(repartitionDTO);
    }

    @PutMapping("/{id}")
    public RepartitionDTO updateRepartition(@PathVariable Long id, @RequestBody RepartitionDTO repartitionDTO) {
        return repartitionService.updateRepartition(id, repartitionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRepartition(@PathVariable Long id) {
        repartitionService.deleteRepartition(id);
    }
}
