package com.example.demo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
@Validated
public class InfoController {
    private final InfoService infoService;

    @GetMapping
    public Collection<InfoDTO> getAll() {
        return infoService.findAll();
    }

    @PostMapping("/add")
    public InfoDTO addNew(@RequestBody @Valid InfoFormDTO infoFormDTO) {
        return infoService.addNewInfo(infoFormDTO);
    }
}
