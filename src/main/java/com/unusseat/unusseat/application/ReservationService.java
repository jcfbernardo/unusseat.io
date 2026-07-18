package com.unusseat.unusseat.application;

import com.unusseat.unusseat.domain.Reservation;
import com.unusseat.unusseat.domain.exceptions.AppExceptions;
import com.unusseat.unusseat.infrastructure.ReservationRepository;
import com.unusseat.unusseat.infrastructure.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class ReservationService {

  private final TicketRepository ticketRepository;
  private final ReservationRepository reservationRepository;

  public ReservationService(
    TicketRepository ticketRep, ReservationRepository repositoryRep
  ) {
    this.ticketRepository = ticketRep;
    this.reservationRepository = repositoryRep;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public Mono<Reservation> reserveTicket(UUID eventId, UUID userId) {
    return ticketRepository.findFirstAvailableByEventId(eventId)
      .switchIfEmpty(Mono.error(new AppExceptions.EventSoldOutException("Ingressos esgotados para este evento.")))
      .flatMap(ticket -> {
        Reservation novaReserva = new Reservation(
          null,
          userId,
          ticket.id(),
          "PENDING",
          Instant.now().plus(10, ChronoUnit.MINUTES),
          null
        );
        return reservationRepository.save(novaReserva);
      })
      .onErrorMap(org.springframework.dao.DataIntegrityViolationException.class,
        e -> new AppExceptions.ConcurrentTicketSoldException(
          "Ops! Alguém finalizou a reserva desse assento milisegundos antes de você. Tente novamente!"))
      .onErrorMap(org.springframework.dao.ConcurrencyFailureException.class,
        e -> new AppExceptions.ConcurrentTicketSoldException(
          "Muitos acessos simultâneos no mesmo setor. Tente novamente!"));
  }

}
