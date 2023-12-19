package com.example.demo;

import lombok.Getter;

@Getter
public class InfoDTO {
    private final Long id;
    private final String info;

    public InfoDTO(Info info) {
        this.id = info.getId();
        this.info = info.getInfo();
    }
}
