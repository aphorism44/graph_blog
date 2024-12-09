package com.dominicjesse.blog.dto;

import java.util.Date;

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
    private Date createdOn;
    private Entry previousEntry;
    private Entry nextEntry;
}

