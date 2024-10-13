package com.dominicjesse.blog.neo4j.repository;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.dominicjesse.blog.neo4j.entity.Entry;


public interface EntryRepository extends Neo4jRepository <Entry, String> {
	
	
}
