package com.dominicjesse.blog.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;
import com.dominicjesse.blog.neo4j.repository.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	EntryRepository entryRepo;
	
	@Override
	public List<Entry> getAllEntries(Account a) {
		return entryRepo.findByCreator(a);
	}

	@Override
	public Entry getFirstEntry(Account a) {
		return entryRepo.getFirstEntry(a.getEmail());
	}

	@Override
	public Entry getPreviousEntry(Entry e) {
        return e.getPreviousEntry() != null ? e.getPreviousEntry() : null;
    }
	
	@Override
    public Entry getNextEntry(Entry e) {
    	return e.getNextEntry() != null ? e.getNextEntry() : null;
    }

	@Override
	public Entry getLatestEntry(Account a) {
		Entry latest = entryRepo.findLatestEntryByAccountId(a.getId());
		return latest != null ? latest : null;
	}

	@Override
	public List<Entry> getEntriesBetweenDates(Account a, Timestamp start, Timestamp end) {
		return entryRepo.findEntriesByAccountIdAndCreatedDateBetween(a.getId(), start, end);
	}

}
