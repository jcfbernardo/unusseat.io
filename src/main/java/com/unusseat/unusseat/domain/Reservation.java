package com.unusseat.unusseat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table(name = "reservations")
public record Reservation (
    @Id UUID id,
    @Column("user_id") UUID userId,
    @Column("ticket_id") UUID ticketId,
    @Column("status") String status,
    @Column("expiration") Instant expiration,
    @Column("created_at") Instant createdAt
) {}
