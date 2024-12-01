package com.dominicjesse.blog.service;

import java.sql.Timestamp;
import java.util.List;

import com.dominicjesse.blog.dto.EntryDto;
import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;

public interface EntryService {
	Entry createFirstEntry(Account a);
	void saveEntry(EntryDto e);
	Entry getFirstEntry(Account a);
	public Entry getPreviousEntry(EntryDto e);
	public Entry getNextEntry(EntryDto e);
	Entry getLatestEntry(Account a);
	List<Entry> getEntriesBetweenDates(Account a, Timestamp start, Timestamp end);
}
