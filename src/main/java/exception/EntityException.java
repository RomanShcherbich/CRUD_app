package exception;

public class EntityException extends RuntimeException{
  public EntityException() {
  }

  public EntityException(Throwable cause) {
    super(cause);
  }

  public EntityException(String message) {
    super(message);
  }

  public EntityException(String message, Throwable cause) {
    super(message, cause);
  }
}
