package com.example.demo.repositories;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user u JOIN employee e ON e.id = u.id WHERE e.email = ?1", nativeQuery = true)
    User findByEmail(String email);

    @Query(value = "SELECT * FROM user u JOIN employee e ON e.id = u.id WHERE e.email = ?1 AND u.password = ?2", nativeQuery = true)
    User Login(String email, String password);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "UPDATE user u JOIN employee e ON e.id = u.id SET password = ?1  WHERE e.email = ?2", nativeQuery = true)
    public void updatePass(String password, String email);

    @Query(value = "SELECT e.id FROM user u JOIN employee e ON e.id = u.id WHERE e.email = ?1", nativeQuery = true)
    public Integer findIdByEmail(String email);
}