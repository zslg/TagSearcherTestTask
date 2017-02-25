package com.example.database.dao;

import com.example.database.domain.TagDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Tag controller
 * Endpoints:
 * - GET www.server.com/tags - list of all tags with pagination and order (?page=1&size=20)
 * - POST www.server.com/tags - add tag, body = {
 *     "name":"" - tag name
 * }
 * - DELETE www.server.com/tags/name - delete tag
 * </pre>
 */

@Transactional
@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface TagDao extends JpaRepository<TagDomain,String> {

}
