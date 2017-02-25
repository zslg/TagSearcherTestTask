package com.example.database.dao;

import com.example.database.domain.PlaceDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Place controller
 * Endpoints:
 * - GET www.server.com/places - list of all places with pagination and order (?page=1&size=20)
 * - POST www.server.com/places - add place, body = {
 *     "name":"" - place name,
 *     "tags":["tag name","tag name"] - list of tag names
 * }
 * - PUT www.server.com/places - edit place, body = {
 *      "id":"place id" - place id
 *     "name":"" - place name,
 *     "tags":["tag name","tag name"] - list of tag names
 * }
 * - DELETE www.server.com/places/name - delete place
 * </pre>
 */
@Transactional
@RepositoryRestResource(collectionResourceRel = "places", path = "places")
public interface PlaceDao extends JpaRepository<PlaceDomain,Long> {

}
