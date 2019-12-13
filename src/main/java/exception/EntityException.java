package exception;

public class EntityException extends Exception{
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
