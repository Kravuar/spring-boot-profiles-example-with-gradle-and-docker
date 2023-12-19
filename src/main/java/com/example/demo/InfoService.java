package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final InfoRepository repository;

    public Collection<InfoDTO> findAll() {
        return repository.findAll().stream()
                .map(InfoDTO::new).toList();
    }

    public InfoDTO addNewInfo(InfoFormDTO infoFormDTO) {
        return new InfoDTO(repository.save(new Info(infoFormDTO)));
    }
}
