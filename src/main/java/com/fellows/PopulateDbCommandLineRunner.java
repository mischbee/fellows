package com.fellows;

import com.fellows.DBRepos.FellowshipRepo;
import com.fellows.DBRepos.InstitutionRepo;
import com.fellows.DBRepos.TagRepo;
import com.fellows.common.model.Fellowship;
import com.fellows.common.model.Institution;
import com.fellows.common.model.Tag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class PopulateDbCommandLineRunner {
    @Bean
    CommandLineRunner commandLineRunner(InstitutionRepo institutionRepo, FellowshipRepo fellowshipRepo, TagRepo tagRepo) {
        return args -> {
            Institution harvard = new Institution("Harvard", "USA", "Boston");
            institutionRepo.save(harvard);

            Tag tag = new Tag("MES");
            Fellowship fellowship = new Fellowship("http://www.google.com", harvard, new Date(1234), null);
            fellowship.addTag(tag);

//            tag.addFellowship(fellowship);
            fellowshipRepo.save(fellowship);
        };
    }
}
