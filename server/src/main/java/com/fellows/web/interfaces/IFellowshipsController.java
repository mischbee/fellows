package com.fellows.web.interfaces;

import com.fellows.common.model.Fellowship;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public interface IFellowshipsController {
    @GetMapping
    List<Fellowship> getFellowships();

    @Validated
    @PostMapping
    Fellowship createFellowship(@RequestBody Fellowship newFellowship);

    @GetMapping("/by-institution")
    Collection<Fellowship> getFellowshipsByInstitution(@RequestParam(name = "id", required = false) String institutionId, @RequestParam(name = "name", required = false) String institutionName);

    @GetMapping("/by-tag")
    Collection<Fellowship> getFellowshipsByTag(@RequestParam(name = "name") String tagName);

    @GetMapping("/find-fellowships")
    Collection<Fellowship> findFellowships(HttpServletRequest request);
}
