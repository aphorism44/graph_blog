package com.dominicjesse.blog.service;

import java.sql.Timestamp;
import java.util.List;

import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;


public interface EntryService {
	Entry createFirstEntry(Account a);
	Entry createNewEntry(String title, String text, Account a, Entry previousEntry);
	void saveEntry(Entry e);
	Entry getFirstEntry(Account a);
	Entry getPreviousEntry(Entry e);
	Entry getNextEntry(Entry e);
	Entry getLatestEntry(Account a);
	List<Entry> getEntriesBetweenDates(Account a, Timestamp start, Timestamp end);
}
