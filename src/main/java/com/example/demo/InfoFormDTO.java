package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record InfoFormDTO(@NotBlank @Length(min = 1) String info) {}
