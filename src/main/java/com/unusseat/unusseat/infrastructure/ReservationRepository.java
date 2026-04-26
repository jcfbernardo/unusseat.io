package com.unusseat.unusseat.infrastructure;

import com.unusseat.unusseat.domain.Reservation;
import com.unusseat.unusseat.domain.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, UUID> {

  @Query("SELECT * FROM reservations WHERE id = :reservationId")
  Mono<User> findByReservationId(UUID reservationId);

  @Modifying
  @Query("UPDATE reservations SET status = 'RESERVED' WHERE id = :ticketsId AND status = 'PENDING'")
  Mono<Integer> reserveTicketAtomically(UUID ticketId);
}
