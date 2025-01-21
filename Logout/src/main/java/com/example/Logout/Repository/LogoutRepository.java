package com.example.Logout.Repository;

import com.example.Logout.Entity.Logout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoutRepository extends MongoRepository<Logout, String > {
}
