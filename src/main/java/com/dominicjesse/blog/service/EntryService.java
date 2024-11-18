package com.dominicjesse.blog.service;

import java.sql.Timestamp;
import java.util.List;

import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;

public interface EntryService {
	List<Entry> getAllEntries(Account a);
	Entry getFirstEntry(Account a);
	public Entry getPreviousEntry(Entry e);
	public Entry getNextEntry(Entry e);
	Entry getLatestEntry(Account a);
	List<Entry> getEntriesBetweenDates(Account a, Timestamp start, Timestamp end);
}
