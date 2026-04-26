package com.unusseat.unusseat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "events")
public record Event (
    @Id UUID id,
    @Column("name") String name,
    @Column("event_date") LocalDate eventDate,
    @Column("total_capacity") Integer totalCapacity
) {}
