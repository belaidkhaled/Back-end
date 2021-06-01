package com.example.demo.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.workflowDoc;

public interface workflowDocRepo extends JpaRepository<workflowDoc, Integer> {

}
