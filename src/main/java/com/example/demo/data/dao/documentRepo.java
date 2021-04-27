package com.example.demo.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.document;



public interface documentRepo extends JpaRepository<document, Integer> {

}
