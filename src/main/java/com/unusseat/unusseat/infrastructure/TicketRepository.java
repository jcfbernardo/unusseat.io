package com.unusseat.unusseat.infrastructure;

import com.unusseat.unusseat.domain.Ticket;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface TicketRepository extends ReactiveCrudRepository<Ticket, UUID> {

  @Query("""
        SELECT t.* FROM tickets t
        WHERE t.event_id = :eventId
        AND t.status = 'AVAILABLE'
        AND t.id NOT IN (
            SELECT ticket_id FROM reservations
            WHERE status IN ('PENDING', 'CONFIRMED')
        )
    """)
  Flux<Ticket> findAvailableTicketsByEventId(UUID eventId);

}
