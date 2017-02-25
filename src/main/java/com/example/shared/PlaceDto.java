package com.example.shared;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
import java.util.Set;

@Document(indexName = "place")
public class PlaceDto {

    private Long id;

    private String name;

    private Set<String> tags;

    private int occurrenceCount;

    public PlaceDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public int getOccurrenceCount() {
        return occurrenceCount;
    }

    public void setOccurrenceCount(List<String> tags) {
        if (tags == null || tags.isEmpty() || this.tags == null || this.tags.isEmpty()) {
            occurrenceCount = 0;
        }else {
            for (String tag : tags) {
                if (this.tags.contains(tag)) {
                    occurrenceCount++;
                }
            }
        }
    }
}
