package com.dominicjesse.blog.utilities;

import java.util.List;
import java.util.stream.Collectors;

import com.dominicjesse.blog.dto.AccountDto;
import com.dominicjesse.blog.dto.EntryDto;
import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;

public class EntityMapper {
	
	public static EntryDto toEntryDto(Entry entry) {
        if (entry == null) {
            return null;
        }

        EntryDto dto = new EntryDto();
        dto.setId(entry.getId());
        dto.setTitle(entry.getTitle());
        dto.setText(entry.getText());
        dto.setCreatedOn(entry.getCreatedOn());
        boolean hasPreviousEntry = entry.getPreviousEntry() != null ? true : false;
        boolean hasNextEntry = entry.getNextEntry() != null ? true : false;
        dto.setHasPreviousEntry(hasPreviousEntry);
        dto.setHasNextEntry(hasNextEntry);
        
        return dto;
    }

    // Convert EntryDto to Entry entity
    public static Entry toEntryEntity(EntryDto EntryDto) {
        if (EntryDto == null) {
            return null;
        }

        Entry entry = new Entry();
        entry.setId(EntryDto.getId());
        entry.setTitle(EntryDto.getTitle());
        entry.setText(EntryDto.getText());
        entry.setCreatedOn(EntryDto.getCreatedOn());

        return entry;
    }

    // Convert Account entity to AccountDto
    public static AccountDto toAccountDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setEmail(account.getEmail());
        dto.setAccountType(account.getAccountType().name());
        dto.setCreatedOn(account.getCreatedOn());
        dto.setLastUpdated(account.getLastUpdated());
        
        return dto;
    }

}
