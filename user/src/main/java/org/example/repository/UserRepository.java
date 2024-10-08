package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
