package com.dominicjesse.blog.neo4j.entity;

import java.time.Instant;
import java.util.Date;
import java.util.List;

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
 
	@Id @GeneratedValue
	private String id;
	
	@Property
	private String email;
	
	@Property
	private Date created_on;
	
	@Property
	private Date last_updated;
	
	@Property
	private AccountType account_type;
	
	@Relationship(type = "HAS_FIRST_ENTRY", direction = Direction.OUTGOING)
	public Entry firstEntry;
	
	@Relationship(type = "HAS_ENTRY", direction = Direction.OUTGOING)
	public List<Entry> entries;

	public Account() {
		this.created_on = Date.from(Instant.now());
	}
	
	public Account(String email, AccountType accountType) {
		this.email = email;
		this.account_type = accountType;
		this.created_on = Date.from(Instant.now());
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void updateTimestamp() {
		this.last_updated = Date.from(Instant.now());
	}
	
	public void updateEmail(String email) {
		this.email = email;
	}
	
	public void updateAccountType(AccountType accountType) {
		this.account_type = accountType;
	}
	
	public String toString() {
	    return this.email.toString() + ": " + this.account_type.name() + ", account created => " + this.created_on;
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