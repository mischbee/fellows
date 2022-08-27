package com.fellows.common.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fellows.common.DBEntity;
import com.fellows.common.Language;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashSet;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity(name = "T_FELLOWSHIP")
public class Fellowship extends DBEntity {
    @URL
    @NotBlank(message = "URL is mandatory")
    @Column(name = "url", columnDefinition = "TEXT")
    private String url;

    @ManyToOne
    private Institution institution;

    @JsonProperty("institutionId")
    @Transient
    private String institutionId;

    @NotNull
    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "fellowship_tags")
    private Collection<Tag> tags;

    private Language language = Language.ENGLISH;

    public Fellowship() {
    }

    public Fellowship(String url, String institutionId, Date dueDate, String name) {
        this.url = url;
        this.dueDate = dueDate;
        this.name = name;
        this.institutionId = institutionId;
    }

    public Fellowship(String url, Institution institution, Date dueDate, String name) {
        this.url = url;
        this.institution = institution;
        this.dueDate = dueDate;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
       if(this.tags == null) this.tags = new LinkedHashSet<>();

       this.tags.add(tag);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
