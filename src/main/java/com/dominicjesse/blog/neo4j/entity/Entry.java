package com.dominicjesse.blog.neo4j.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.dominicjesse.blog.enums.EntryVisibility;


@Node("neo4j")
public class Entry {

  @Id @GeneratedValue
  private Long id;

  @Property
  private String text;
  
  @Property
  private Account creator;
  
  @Property
  private EntryVisibility visibility;
  
  @Property
  private Timestamp createdOn;
  
  @Property
  private Timestamp lastUpdated;
  

  public Entry(String text, Account creator, EntryVisibility visibility) {
    this.text = text;
    this.creator = creator;
    this.visibility = visibility;
    this.createdOn = Timestamp.valueOf(LocalDateTime.now());
  }

  /**
   * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
   * to ignore the direction of the relationship.
   * https://dzone.com/articles/modelling-data-neo4j
   */
  @Relationship(type = "HAS_PREVIOUS_ENTRY", direction = Direction.OUTGOING)
  public Entry previousEntry;
  @Relationship(type = "HAS_NEXT_ENTRY", direction = Direction.OUTGOING)
  public Entry nextEntry;

  public void addEntryInOrder(Entry last) {
    last.nextEntry = this;
    this.previousEntry = last;
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
	    return this.creator.toString() + ", entry created => " + this.createdOn;
  }
  
}
