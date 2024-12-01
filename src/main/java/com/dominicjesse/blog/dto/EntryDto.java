package com.dominicjesse.blog.dto;

import java.sql.Timestamp;

import com.dominicjesse.blog.neo4j.entity.Entry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EntryDto {

    private String id;
    private String title;
    private String text;
    private Timestamp createdOn;
    
    public EntryDto(Entry e) {
    	this.id = e.getId();
    	this.title = e.getTitle();
    	this.text = e.getText();
    	this.createdOn = e.getCreatedOn();
    }

   
}

