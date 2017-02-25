package com.example.config;

import com.example.database.domain.PlaceDomain;
import com.example.database.domain.TagDomain;
import com.example.database.domain.UserDomain;
import com.example.rest.SearchController;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;

@Configuration
public class RestRepositoriesConfig extends RepositoryRestMvcConfiguration {
    @Override
    public RepositoryRestConfiguration config() {
        RepositoryRestConfiguration config = super.config();
        config.exposeIdsFor(TagDomain.class, PlaceDomain.class, UserDomain.class,SearchController.class);
        return config;
    }

    @Override
    public RepositoryEntityLinks entityLinks() {
        RepositoryEntityLinks repositoryEntityLinks = super.entityLinks();
        return repositoryEntityLinks;
    }
}
