package com.fellows.web.impls;


import com.fellows.DBRepos.FellowshipRepo;
import com.fellows.DBRepos.TagRepo;
import com.fellows.common.model.Fellowship;
import com.fellows.common.model.Tag;
import com.fellows.web.interfaces.ITagController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tags")
public class TagContollerImpl implements ITagController {

    @Autowired
    TagRepo tagRepo;

    @Autowired
    FellowshipRepo fellowshipRepo;

    @Override
    public List<Tag> getTags(String name) {
        return tagRepo.findAll();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepo.findById(name).orElse(null);
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepo.save(tag);
    }

    public Fellowship addTagToFellowship(Tag tag, String fellowshipId) {
        Fellowship fellowship = fellowshipRepo.findById(UUID.fromString(fellowshipId)).orElse(null);
        if (fellowship == null) return null;

        fellowship.addTag(tag);
        tag.addFellowship(fellowship);

        fellowshipRepo.save(fellowship);

        return fellowship;
    }

}
