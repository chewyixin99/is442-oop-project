package com.is442.oop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.is442.oop.data.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    // Inherits save, findAll, findById methods
}
