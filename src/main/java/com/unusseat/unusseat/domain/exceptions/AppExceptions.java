package com.unusseat.unusseat.domain.exceptions;

public final class AppExceptions {

  public static sealed abstract class BaseException extends RuntimeException
  permits EventSoldOutException, ConcurrentTicketSoldException {
    public BaseException(String message) {
      super(message);
    }
  }

  public static final class EventSoldOutException extends BaseException {
    public EventSoldOutException(String message) {
      super(message);
    }
  }

  public static final class ConcurrentTicketSoldException extends BaseException {
    public ConcurrentTicketSoldException(String message) {
      super(message);
    }
  }
}
