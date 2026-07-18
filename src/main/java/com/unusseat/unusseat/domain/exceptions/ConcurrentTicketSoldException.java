package com.unusseat.unusseat.domain.exceptions;

public class ConcurrentTicketSoldException extends RuntimeException {
  public ConcurrentTicketSoldException(String message) {
    super(message);
  }
}
