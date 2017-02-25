package com.example.config;

import com.example.database.domain.UserDomain;
import com.example.rest.SearchController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserPlacesLinks implements ResourceProcessor<Resource<UserDomain>> {

    @Override
    public Resource<UserDomain> process(Resource<UserDomain> resource) {
        UserDomain content = resource.getContent();
        resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(SearchController.class).searchByTags(content.getId())).withRel("places"));
        return resource;
    }
}
