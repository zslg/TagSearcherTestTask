package com.example.config.handlers;

import com.example.database.domain.PlaceDomain;
import com.example.database.domain.TagDomain;
import com.example.elasticsearch.PlaceElasticSearchDao;
import com.example.service.Converter;
import com.example.shared.PlaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;


@Configuration
@RepositoryEventHandler
public class RestRepositoriesHandler {

    @Autowired
    private PlaceElasticSearchDao placeElasticSearchDao;

    @Autowired
    private Converter converter;

    @HandleAfterCreate
    public void handleTagSave(TagDomain tagDomain) {

    }

    @HandleAfterSave
    @HandleAfterCreate
    public void handlePlaceSave(PlaceDomain placeDomain) {
        PlaceDto placeDto = converter.toPlaceDto(placeDomain);
        if (placeDto != null) {
            placeElasticSearchDao.save(placeDto);
        }
    }

    @HandleAfterDelete
    public void handlePlaceDate(PlaceDomain placeDomain) {
        Long id = placeDomain.getId();
        if (placeElasticSearchDao.findOne(id) != null) {
            placeElasticSearchDao.delete(id);
        }
    }
}

