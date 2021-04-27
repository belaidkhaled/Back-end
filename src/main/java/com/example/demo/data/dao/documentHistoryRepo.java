package com.example.demo.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.documentHistory;

public interface documentHistoryRepo extends JpaRepository<documentHistory, Integer> {

}
