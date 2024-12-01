package com.dominicjesse.blog.neo4j.repository;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;


public interface EntryRepository extends Neo4jRepository <Entry, String> {
	
	@Query("MATCH (a:Account)-[:HAS_FIRST_ENTRY]->(e:Entry) WHERE a.email = $email RETURN e")
	Entry getFirstEntry(String email);
	
	Entry findPreviousEntryById(String id);
	
	Entry findNextEntryById(String id);
	
	@Query("MATCH (a:Account {id: $accountId})-[:HAS_FIRST_ENTRY]->(firstEntry:Entry) " +
	           "MATCH path = (firstEntry)-[:HAS_NEXT_ENTRY*0..]->(lastEntry:Entry) " +
	           "WHERE NOT (lastEntry)-[:HAS_NEXT_ENTRY]->() " +
	           "RETURN lastEntry")
	Entry findLatestEntryByAccountId(String accountId);
	
	
	@Query("MATCH (a:Account {id: $accountId})-[:HAS_FIRST_ENTRY|HAS_NEXT_ENTRY*0..]->(entry:Entry) " +
		       "WHERE entry.createdOn >= $startDate AND entry.createdOn <= $endDate " +
		       "RETURN entry")
	List<Entry> findEntriesByAccountIdAndCreatedDateBetween(String accountId, Timestamp startDate, Timestamp endDate);

}
