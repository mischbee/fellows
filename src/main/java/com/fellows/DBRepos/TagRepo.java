package com.fellows.DBRepos;

import com.fellows.common.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepo extends JpaRepository<Tag, String> {
}
