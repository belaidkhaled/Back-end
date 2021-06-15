package com.example.demo.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.user;

public interface userRepo extends JpaRepository<user, Integer> {

}
