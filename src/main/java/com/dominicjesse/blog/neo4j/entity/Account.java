package com.dominicjesse.blog.neo4j.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.dominicjesse.blog.enums.AccountType;

import lombok.Getter;
import lombok.Setter;

@Node("Account")
@Getter
public class Account {
	
	@Id
	@Setter
	private String id;
	
	@Property
	@Setter
	private String email;
	
	@Property
	private Date createdOn;
	
	@Property
	@Setter
	private Date lastUpdated;
	
	@Property
	@Setter
	private AccountType accountType;
	
	@Relationship(type = "HAS_FIRST_ENTRY", direction = Direction.OUTGOING)
	@Setter
	private Entry firstEntry;
	
	@Relationship(type = "HAS_ENTRY", direction = Direction.OUTGOING)
	@Setter
	private List<Entry> entries;
	
	//Default constructor needed by Spring Data Neo4j
	public Account() {
		this.id = UUID.randomUUID().toString();
		this.createdOn = Timestamp.valueOf(LocalDateTime.now());
		this.entries = new ArrayList<Entry>();
	}
	
	public Account(String email, AccountType accountType) {
		this();
		this.email = email;
		this.accountType = accountType;
	}
	
	public int getNumberOfEntries() {
		return this.entries.size();
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