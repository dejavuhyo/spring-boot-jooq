package com.example.jooq.service;

import com.example.jooq.dto.UserDto;
import com.example.jooq.tables.records.UsersRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.jooq.tables.Users.USERS;

@Service
@RequiredArgsConstructor
public class UserService {

    private final DSLContext dsl;

    // Create
    public UserDto createUser(UserDto userDto) {
        UsersRecord usersRecord = dsl.insertInto(USERS)
                .set(USERS.NAME, userDto.getName())
                .set(USERS.EMAIL, userDto.getEmail())
                .returning()
                .fetchOne();

        return toDto(usersRecord);
    }

    // Read All
    public List<UserDto> getAllUsers() {
        return dsl.selectFrom(USERS)
                .fetch()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Read by ID
    public Optional<UserDto> getUserById(Integer id) {
        return dsl.selectFrom(USERS)
                .where(USERS.ID.eq(id))
                .fetchOptional()
                .map(this::toDto);
    }

    // Update
    public Optional<UserDto> updateUser(Integer id, UserDto userDto) {
        UsersRecord updatedRecord = dsl.update(USERS)
                .set(USERS.NAME, userDto.getName())
                .set(USERS.EMAIL, userDto.getEmail())
                .where(USERS.ID.eq(id))
                .returning()
                .fetchOne();

        return Optional.ofNullable(updatedRecord).map(this::toDto);
    }

    // Delete
    public boolean deleteUser(Integer id) {
        int deletedRows = dsl.deleteFrom(USERS)
                .where(USERS.ID.eq(id))
                .execute();

        return deletedRows == 1;
    }

    private UserDto toDto(UsersRecord record) {
        if (record == null) {
            return null;
        }
        return new UserDto(
                record.getId(),
                record.getName(),
                record.getEmail(),
                record.getCreatedAt()
        );
    }
}
