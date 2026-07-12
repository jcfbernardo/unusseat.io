package com.unusseat.unusseat.infrastructure;

import com.unusseat.unusseat.domain.Event;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EventRepository extends ReactiveCrudRepository<Event, UUID> {

  @Query("SELECT * FROM events WHERE id = :eventId")
  Mono<Event> findByEventId(UUID eventId);
}
