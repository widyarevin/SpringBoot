package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // User findByEmail(String email);
    @Query(value = "SELECT rl.id FROM Role rl WHERE rl.level = (SELECT MAX(rl.level) FROM Role rl)", nativeQuery = true)
    int findByEmail();
}
