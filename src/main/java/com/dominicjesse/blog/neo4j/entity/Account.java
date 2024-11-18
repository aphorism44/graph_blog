package com.dominicjesse.blog.neo4j.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.dominicjesse.blog.enums.AccountType;

import lombok.Getter;

@Node("Account")
@Getter
public class Account {
	
	@Id
	private String id;
	
	@Property
	private String email;
	
	@Property
	private Date createdOn;
	
	@Property
	private Date lastUpdated;
	
	@Property
	private AccountType accountType;
	
	@Relationship(type = "HAS_FIRST_ENTRY", direction = Direction.OUTGOING)
	public Entry firstEntry;
	
	@Relationship(type = "HAS_ENTRY", direction = Direction.OUTGOING)
	public List<Entry> entries;
	
	//Default constructor needed by Spring Data Neo4j
	public Account() {
		this.id = UUID.randomUUID().toString();
		this.createdOn = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public Account(String email, AccountType accountType) {
		this();
		this.email = email;
		this.accountType = accountType;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void updateTimestamp() {
		this.lastUpdated = Date.from(Instant.now());
	}
	
	public void updateEmail(String email) {
		this.email = email;
	}
	
	public void updateAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public void setFirstEntry(Entry entry) {
        this.firstEntry = entry;
    }
	
	public int getNumberOfEntries() {
		return this.entries.size();
	}
	
	public List<Entry> getAllEntries() {
		return this.entries;
	}
	
	public void addEntry(Entry entry) {
		this.entries.add(entry);
	}
	
	public String toString() {
	    return this.email.toString() + ": " + this.accountType.name() + ", account created => " + this.createdOn;
    }
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
	
	
	
}