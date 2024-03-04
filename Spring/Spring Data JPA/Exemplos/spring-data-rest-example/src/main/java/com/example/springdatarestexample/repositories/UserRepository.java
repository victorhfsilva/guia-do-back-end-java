package com.example.springdatarestexample.repositories;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.springdatarestexample.entities.WebsiteUser;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<WebsiteUser, Long> {
    
    List<WebsiteUser> findByName(String name);

    List<WebsiteUser> findByNameContaining(String keyword);

    List<WebsiteUser> findByEmailContaining(String keyword);
}