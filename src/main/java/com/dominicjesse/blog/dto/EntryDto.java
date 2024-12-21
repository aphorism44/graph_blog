package com.dominicjesse.blog.dto;

import java.time.LocalDateTime;

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
    private LocalDateTime createdOn;
    boolean hasPreviousEntry;
    boolean hasNextEntry;
}

