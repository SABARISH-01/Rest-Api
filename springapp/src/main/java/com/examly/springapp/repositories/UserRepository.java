package com.examly.springapp.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
     Page<User> findAll(Pageable pageable);
     @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> searchUsersByName(String name);
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.name = :name")
    int deleteByName(String name);
}