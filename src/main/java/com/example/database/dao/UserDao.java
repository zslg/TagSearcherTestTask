package com.example.database.dao;

import com.example.database.domain.PlaceDomain;
import com.example.database.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * User controller
 * Endpoints:
 * - GET www.server.com/users - list of all users with pagination and order (?page=1&size=20)
 * - POST www.server.com/users - add user, body = {
 *     "name":"" - user name,
 *     "tags":["tag name","tag name"] - list of tag names
 * }
 * - PUT www.server.com/users - edit user, body = {
 *      "id":"user id" - user id
 *     "name":"" - user name,
 *     "tags":["tag name","tag name"] - list of tag names
 * }
 * - DELETE www.server.com/users/name - delete user
 * </pre>
 */

@Transactional
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserDao extends JpaRepository<UserDomain,Long> {

}
