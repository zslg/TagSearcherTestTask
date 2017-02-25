package com.example.elasticsearch;

import com.example.shared.PlaceDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RestResource(exported=false)
public interface PlaceElasticSearchDao extends ElasticsearchCrudRepository<PlaceDto,Long> {

    List<PlaceDto> findByTagsIn(List<String> tags);

}
