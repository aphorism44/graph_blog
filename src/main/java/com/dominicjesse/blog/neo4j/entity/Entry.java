package com.dominicjesse.blog.neo4j.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import com.dominicjesse.blog.enums.EntryVisibility;

import lombok.Getter;
import lombok.Setter;


@Node("Entry")
@Getter
public class Entry {

  @Id
  @Setter
  private String id;

  @Property
  @Setter
  private String text;
  
  @Property
  @Setter
  private String title;
  
  /**
   * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
   * to ignore the direction of the relationship.
   * https://dzone.com/articles/modelling-data-neo4j
   */
  
  @Relationship(type = "HAS_PREVIOUS_ENTRY", direction = Direction.OUTGOING)
  @Setter
  public Entry previousEntry;
  
  @Relationship(type = "HAS_NEXT_ENTRY", direction = Direction.OUTGOING)
  @Setter
  public Entry nextEntry;
  
  @Property
  @Setter
  private EntryVisibility visibility;
  
  @Property
  @Setter
  private Date createdOn;
  
  @Property
  @Setter
  private Date lastUpdated;
  
  //Default constructor needed by Spring Data Neo4j
  public Entry() {
      this.id = UUID.randomUUID().toString();
      this.createdOn = Timestamp.valueOf(LocalDateTime.now());
      this.title = "";
  }

  public Entry(String text, String title, EntryVisibility visibility) {
	this();
    this.text = text;
    this.title = title;
    this.visibility = visibility;
  }
  
  public void addEntryInOrder(Entry last) {
    last.nextEntry = this;
    this.previousEntry = last;
  }
  
  public String toString() {
	    return "\"" + this.text + "\", entry created => " + this.createdOn;
  }
  
}
