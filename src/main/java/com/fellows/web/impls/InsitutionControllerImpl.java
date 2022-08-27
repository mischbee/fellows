package com.fellows.web.impls;

import com.fellows.common.model.Institution;
import com.fellows.DBRepos.InstitutionRepo;
import com.fellows.web.interfaces.IInsitutionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/institutions")
public class InsitutionControllerImpl implements IInsitutionController {

    @Autowired
    private InstitutionRepo repo;

    @Override
    public List<Institution> getAllInstitutions() {
        return this.repo.findAll();
    }

    @Override
    public Institution createInstitution(Institution institution) {
        return this.repo.save(institution);
    }
}
