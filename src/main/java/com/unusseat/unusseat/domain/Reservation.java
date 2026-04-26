package com.unusseat.unusseat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table(name = "reservations")
public record Reservation (
    @Id UUID id,
    @Column("user_id") User user,
    @Column("ticket_id") Ticket ticket,
    @Column("status") String status,
    @Column("expiration") OffsetDateTime expiration,
    @Column("created_at") OffsetDateTime createdAt
) {}
