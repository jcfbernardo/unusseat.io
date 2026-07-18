package com.unusseat.unusseat.domain.exceptions;

public class EventSoldOutException extends RuntimeException {
  public EventSoldOutException(String message) {
    super(message);
  }
}
