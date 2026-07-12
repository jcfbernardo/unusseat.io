package com.unusseat.unusseat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table(name = "users")
public record User (
    @Id UUID id,
    @Column("name") String name,
    @Column("email") String email,
    @Column("creation_date") Instant creationDate
) {}
