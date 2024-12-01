package com.dominicjesse.blog.utilities;

import java.util.List;
import java.util.stream.Collectors;

import com.dominicjesse.blog.dto.AccountDto;
import com.dominicjesse.blog.dto.EntryDto;
import com.dominicjesse.blog.neo4j.entity.Account;
import com.dominicjesse.blog.neo4j.entity.Entry;

public class EntityMapper {
	START HERE
	public static EntryDto toEntryDto(Entry entry) {
        if (entry == null) {
            return null;
        }

        EntryDto dto = new EntryDto();
        dto.setId(entry.getId());
        dto.setTitle(entry.getTitle());
        dto.setText(entry.getText());
        dto.setCreatedOn(entry.getCreatedOn());

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

        // Convert the list of Entry entities to EntryDtos
        List<EntryDto> EntryDtos = account.getEntries() != null ? 
                account.getEntries().stream()
                .map(EntityMapper::toEntryDto)
                .collect(Collectors.toList()) : null;
                
        dto.setEntries(EntryDtos);

        return dto;
    }

    // Convert AccountDto to Account entity
    public static Account toAccountEntity(AccountDto AccountDto) {
        if (AccountDto == null) {
            return null;
        }

        Account account = new Account();
        account.setId(AccountDto.getId());
        account.setEmail(AccountDto.getEmail());

        // Convert the list of EntryDtos to Entry entities
        List<Entry> entries = AccountDto.getEntries() != null ? 
                AccountDto.getEntries().stream()
                .map(EntityMapper::toEntryEntity)
                .collect(Collectors.toList()) : null;

        account.setEntries(entries);

        return account;
    }

}
