package com.library.mariela.authservice.authservice.repositories;

import com.library.mariela.authservice.authservice.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
