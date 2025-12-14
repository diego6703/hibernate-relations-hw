package mate.academy.hibernate.relations.model;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable ex) {
        super(message, ex);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}
