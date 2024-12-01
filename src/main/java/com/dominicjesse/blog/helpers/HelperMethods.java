package com.dominicjesse.blog.helpers;

import com.dominicjesse.blog.dto.EntryDto;
import com.dominicjesse.blog.neo4j.entity.Entry;

public class HelperMethods {
	
    private EntryDto convertToDto(Entry entry) {
    	EntryDto entryDTO = new EntryDto();
        entryDTO.setId(entry.getId());
        entryDTO.setTitle(entry.getTitle());
        entryDTO.setText(entry.getText());
        entryDTO.setCreatedOn(entry.getCreatedOn());
        return entryDTO;
    }
    
    
	
}
