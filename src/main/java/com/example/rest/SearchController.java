package com.example.rest;

import com.example.database.dao.UserDao;
import com.example.database.domain.UserDomain;
import com.example.elasticsearch.PlaceElasticSearchDao;
import com.example.shared.PlaceDto;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <pre>
 * GET www.server.com/search?userId=?
 * Endpoint to get top 10 user places
 * </pre>
 */

@RestController
public class SearchController {

    @Autowired
    private PlaceElasticSearchDao placeElasticSearchDao;

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/search")
    public ResponseEntity<Collection<PlaceDto>> searchByTags(@NotNull @NotEmpty @RequestParam("userId") Long userId){

        UserDomain userDomain = userDao.findOne(userId);
        if (userDomain == null) {
            throw new ValidationException("User with id="+userId+" not found;");
        }

        List<String> tags = userDomain.getTags().stream().map(QueryParser::escape).collect(Collectors.toList());
        List<PlaceDto> result = placeElasticSearchDao.findByTagsIn(tags);
        result.forEach(placeDto -> placeDto.setOccurrenceCount(tags));
        List<PlaceDto> top10Results = result.stream().sorted(Comparator.comparingLong(PlaceDto::getOccurrenceCount).reversed()).limit(10).collect(Collectors.toList());
        return ResponseEntity.ok(top10Results);

    }

}
