package com.dominicjesse.blog.neo4j.repository;


import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

import com.dominicjesse.blog.neo4j.entity.Entry;


public interface EntryRepository extends ReactiveNeo4jRepository <Entry, Long> {
	
	List<Entry> findByCreator_Email(String email);
	Entry findFirstByCreator_EmailOrderByCreatedOnAsc(String email);
	Entry findFirstByCreator_EmailOrderByCreatedOnDesc(String email);
	List<Entry> findAllByCreator_EmailAndCreatedOnBetween(String email, Timestamp start, Timestamp end);
	
}
