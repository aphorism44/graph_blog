package com.dominicjesse.blog.neo4j.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.dominicjesse.blog.enums.EntryVisibility;


@Node("Entry")
public class Entry {

  @Id
  private String id;

  @Property
  private String text;
  
  /**
   * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
   * to ignore the direction of the relationship.
   * https://dzone.com/articles/modelling-data-neo4j
   */
  @Relationship(type = "CREATED_BY", direction = Direction.OUTGOING)
  private Account creator;
  
  @Relationship(type = "HAS_PREVIOUS_ENTRY", direction = Direction.OUTGOING)
  public Entry previousEntry;
  
  @Relationship(type = "HAS_NEXT_ENTRY", direction = Direction.OUTGOING)
  public Entry nextEntry;
  
  @Property
  private EntryVisibility visibility;
  
  @Property
  private Timestamp createdOn;
  
  @Property
  private Timestamp lastUpdated;
  
  //Default constructor needed by Spring Data Neo4j
  public Entry() {
      this.id = UUID.randomUUID().toString();
      this.createdOn = Timestamp.valueOf(LocalDateTime.now());
  }

  public Entry(String text, Account creator, EntryVisibility visibility) {
	this();
    this.text = text;
    this.creator = creator;
    this.visibility = visibility;
  }
  
  public void addEntryInOrder(Entry last) {
    last.nextEntry = this;
    this.previousEntry = last;
  }
  
  public Entry getPreviousEntry() {
	  return this.previousEntry;
  }
  
  public Entry getNextEntry() {
	  return this.nextEntry;
  }
  
  public String getText() {
    return text;
  }
  public void updateText(String txt) {
    this.text = txt;
  }
  
  public void changeVisibility(EntryVisibility visibility) {
	  this.visibility = visibility;
  }
  
  public String toString() {
	    return this.creator.getEmail() + ", entry created => " + this.createdOn;
  }
  
}
