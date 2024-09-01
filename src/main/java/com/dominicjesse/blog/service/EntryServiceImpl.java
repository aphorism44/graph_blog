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
		return entryRepo.findByCreator_Email(a.getEmail());
	}

	@Override
	public Entry getFirstEntry(Account a) {
		return entryRepo.findFirstByCreator_EmailOrderByCreatedOnAsc(a.getEmail());
	}

	@Override
	public Entry getLatestEntry(Account a) {
		return entryRepo.findFirstByCreator_EmailOrderByCreatedOnDesc(a.getEmail());
	}

	@Override
	public List<Entry> getEntriesBetweenDates(Account a, Timestamp start, Timestamp end) {
		return entryRepo.findAllByCreator_EmailAndCreatedOnBetween(a.getEmail(), start, end);
	}

}
