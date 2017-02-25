package com.example.service;

import com.example.database.domain.PlaceDomain;
import com.example.shared.PlaceDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Converter {

    public PlaceDto toPlaceDto(PlaceDomain placeDomain) {
        if (placeDomain == null) {
            return null;
        }
        PlaceDto placeDto = new PlaceDto();
        placeDto.setId(placeDomain.getId());
        placeDto.setName(placeDomain.getName());
        Set<String> tags = placeDomain.getTags();
        placeDto.setTags(new HashSet<>(tags));
        return placeDto;
    }

}
