package com.fellows.web.interfaces;

import com.fellows.common.model.Institution;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IInsitutionController {
    @GetMapping
    List<Institution> getAllInstitutions();

    @PostMapping
    Institution createInstitution(@RequestBody Institution institution);
}
