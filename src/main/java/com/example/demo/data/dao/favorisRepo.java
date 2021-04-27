package com.example.demo.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.Favoris;

public interface favorisRepo extends JpaRepository<Favoris, Integer> {

}
