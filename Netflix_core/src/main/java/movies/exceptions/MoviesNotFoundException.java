package movies.exceptions;

public class MoviesNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public MoviesNotFoundException() {
        super();
    }

    public MoviesNotFoundException(String customMessage) {
        super(customMessage);
    }
}
