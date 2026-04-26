package com.unusseat.unusseat.infrastructure;

import com.unusseat.unusseat.domain.Ticket;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TicketRepository extends ReactiveCrudRepository<Ticket, UUID> {

  @Query("SELECT * FROM tickets WHERE event_id = :eventId AND status = 'AVAILABLE'")
  Flux<Ticket> findAvailableTicketsByEventId(UUID eventId);

  @Modifying
  @Query("UPDATE tickets SET status = 'RESERVED' WHERE id = :ticketsId AND status = 'AVAILABLE'")
  Mono<Integer> reserveTicketAtomically(UUID ticketId);
}
