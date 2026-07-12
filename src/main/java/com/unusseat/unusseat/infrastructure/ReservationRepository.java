package com.unusseat.unusseat.infrastructure;

import com.unusseat.unusseat.domain.Reservation;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, UUID> {

  @Query("SELECT * FROM reservations WHERE id = :reservationId")
  Mono<Reservation> findByReservationId(UUID reservationId);

  Flux<Reservation> findByUserId(UUID userId);

  @Modifying
  @Query("UPDATE reservations SET status = 'CONFIRMED' WHERE id = :reservationId AND status = 'PENDING'")
  Mono<Integer> confirmReservation(UUID reservationId);

  @Modifying
  @Query("UPDATE reservations SET status = 'EXPIRED' WHERE status = 'PENDING' AND expiration <= NOW()")
  Mono<Integer> expirePendingReservations();
}
