package com.fellows.common.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name")
@Entity(name = "T_TAG")
public class Tag implements ISearchable{

    @Id
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    private Collection<Fellowship> fellowships;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Fellowship> getFellowships() {
        return fellowships;
    }

    public void setFellowships(Collection<Fellowship> fellowships) {
        this.fellowships = fellowships;
    }

    public void addFellowship(Fellowship fellowship) {
        if (this.fellowships == null) fellowships = new LinkedHashSet<>();

        this.fellowships.add(fellowship);
    }
}
