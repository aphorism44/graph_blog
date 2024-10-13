package com.dominicjesse.blog.neo4j.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.dominicjesse.blog.enums.AccountType;

import lombok.Getter;

@Node("neo4j")
@Getter
public class Account {
 
	@Id @GeneratedValue
	private Long id;
	
	@Property
	private String email;
	
	@Property
	private String userId;
	
	@Property
	private Timestamp createdOn;
	
	@Property
	private Timestamp lastUpdated;
	
	@Property
	private AccountType accountType;
	
	@Relationship(type = "HAS_FIRST_ENTRY", direction = Direction.OUTGOING)
	public Entry firstEntry;
	
	@Relationship(type = "HAS_ENTRY", direction = Direction.OUTGOING)
	public List<Entry> entries;

	public Account() {
		this.createdOn = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public Account(String email, AccountType accountType, String mysqlId) {
		this.email = email;
		this.accountType = accountType;
		this.createdOn = Timestamp.valueOf(LocalDateTime.now());
		this.userId = mysqlId;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void updateTimestamp() {
		this.lastUpdated = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public void updateEmail(String email) {
		this.email = email;
	}
	
	public void updateAccountType(AccountType accountType) {
		this.accountType = accountType;
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