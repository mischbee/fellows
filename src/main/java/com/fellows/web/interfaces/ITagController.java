package com.fellows.web.interfaces;

import com.fellows.common.model.Fellowship;
import com.fellows.common.model.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ITagController {
    @GetMapping
    List<Tag> getTags( String name);

    @GetMapping("/by")
    Tag getTagByName(@RequestParam String name);

    @PostMapping
    Tag createTag(@RequestBody Tag tag);

    @PostMapping("/add-tag-to-fellowship")
    Fellowship addTagToFellowship(@RequestBody Tag tag, @RequestParam String fellowshipId);
}
