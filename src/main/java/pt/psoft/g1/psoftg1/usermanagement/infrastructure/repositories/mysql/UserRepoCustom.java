package pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql;

import pt.psoft.g1.psoftg1.shared.services.Page;
import pt.psoft.g1.psoftg1.usermanagement.model.User;
import pt.psoft.g1.psoftg1.usermanagement.services.SearchUsersQuery;

import java.util.List;

/**
 * Custom interface to add custom methods to spring repository.
 *
 * @see https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations
 */
interface UserRepoCustom {

    List<User> searchUsers(Page page, SearchUsersQuery query);
}
