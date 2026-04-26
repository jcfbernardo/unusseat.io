package com.unusseat.unusseat.infrastructure;

import com.unusseat.unusseat.domain.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

  @Query("SELECT * FROM users WHERE id = :userId")
  Mono<User> findByUserId(UUID userId);
}
