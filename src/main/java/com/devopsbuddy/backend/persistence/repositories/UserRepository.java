package com.devopsbuddy.backend.persistence.repositories;

import com.devopsbuddy.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nvishwarupe
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /** Returns a user given username and null if not found
     * @param username
     * return User
     */
    public User findByUsername(String username);
}
