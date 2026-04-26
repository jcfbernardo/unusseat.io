package com.unusseat.unusseat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table("tickets")
public record Ticket (
  @Id UUID id,
  @Column("event_id") UUID eventId,
  @Column("seat_identifier") String seatIdentifier,
  BigDecimal price,
  String status
) {}
