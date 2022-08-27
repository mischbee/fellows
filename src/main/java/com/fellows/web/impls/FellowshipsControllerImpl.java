package com.fellows.web.impls;

import com.fellows.common.model.Fellowship;
import com.fellows.DBRepos.FellowshipRepo;
import com.fellows.common.model.ISearchable;
import com.fellows.common.model.Institution;
import com.fellows.DBRepos.InstitutionRepo;
import com.fellows.web.interfaces.IFellowshipsController;
import com.google.common.base.Functions;
import com.google.common.reflect.ClassPath;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.SQLCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fellowships")
public class FellowshipsControllerImpl implements IFellowshipsController {
    @Autowired
    InstitutionRepo institutionRepo;

    @Autowired
    FellowshipRepo fellowshipRepo;

    @Autowired
    EntityManager entityManager;

    private Map<String, Class<ISearchable>> classes;

    private static final String packageName = "com.fellows.common.model";

    public Map<String, Class<ISearchable>> getClasses() throws IOException {
        if (classes == null) this.setClasses();
        return classes;
    }

    public void setClasses() throws IOException {
        this.classes = ClassPath.from(ClassLoader.getSystemClassLoader())
                .getTopLevelClasses("com.fellows.common.model")
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> List.of(clazz.getInterfaces()).contains(ISearchable.class))
                .map(clazz -> (Class<ISearchable>) clazz)
                .collect(Collectors.toMap(Class::getName, Functions.identity()));
    }

    @Override
    public List<Fellowship> getFellowships() {
        return this.fellowshipRepo.findAll();
    }

    @Override
    public Fellowship createFellowship(Fellowship newFellowship) {
        Institution institution = getInstitutionById(newFellowship.getInstitutionId());
        newFellowship.setInstitution(institution);
        return this.fellowshipRepo.save(newFellowship);
    }

    @Override
    public Collection<Fellowship> getFellowshipsByInstitution(String institutionId, String institutionName) {
        if (StringUtils.isNotBlank(institutionId))
            return this.fellowshipRepo.findAllFellowshipsForInstitution(getInstitutionById(institutionId).getId());

        return this.fellowshipRepo.findAllFellowshipsForInstitution(institutionName);

    }

    @Override
    public Collection<Fellowship> getFellowshipsByTag(String tagName) {
        return this.fellowshipRepo.findAllFellowshipsForTag(tagName);
    }

    @Override
    public List<Fellowship> findFellowships(HttpServletRequest request) {

        Map<String, String[]> params = request.getParameterMap();

        Map<String, Class<ISearchable>> clazzes;
        try {
            clazzes = this.getClasses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Map<Class<ISearchable>, String[]> values = new HashMap<>();
        params.forEach((key, value) -> {
            Class<ISearchable> clazz = clazzes.get(packageName + "." + key);
            if(clazz == null) return;
            Field field = Arrays.stream(clazz.getDeclaredFields()).filter(f -> f.isAnnotationPresent(Id.class)).findFirst().orElse(null);

            values.put(clazz, value);
        });
        String sql = "SELECT * FROM Fellowships f where f.? = ?.?";
        //Class Tag --> Tag1, Tag2

        //SELECT * FROM FEllowhsips where TAG.name = tag1 OR TAG.NAME = tag2
        TypedQuery<Fellowship> typedQuery = entityManager.createQuery("", Fellowship.class);
        return null;
    }

    private Institution getInstitutionById(String id) {
        return institutionRepo.findById(UUID.fromString(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid institution id"));
    }

}
