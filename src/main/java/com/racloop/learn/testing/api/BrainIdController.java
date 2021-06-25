package com.racloop.learn.testing.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/id")
@Slf4j
public class BrainIdController {

    private final BrainIdService brainIdService;

    public BrainIdController(BrainIdService brainIdService) {
        this.brainIdService = brainIdService;
    }

    @GetMapping({"/", ""})
    public List<BrainId> all() {
        log.info("all");
        return brainIdService.all();
    }

    @GetMapping({"/search", "/search/"})
    public List<BrainId> search(@RequestParam(value = "domain", defaultValue = "common") String domain) {
        log.info("search : " + domain);
        return brainIdService.search(domain);
    }

    @PostMapping({"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public BrainId create(@Valid @RequestBody BrainId brainId) {
        return brainIdService.save(brainId);
    }

    @PutMapping("/edit/{id}")
    public BrainId edit(@PathVariable Long id, @RequestBody BrainId brainId) {
        log.info("Edit brain id with id: " + id);
        return brainIdService.edit(id, brainId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        brainIdService.delete(id);
    }
}
