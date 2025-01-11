package amazonApi_v01.exception;

public class DuplicateFieldException extends RuntimeException{
    public DuplicateFieldException(String field, String value) {
        super(field + "With value " + value + " is already in use");
    }
}
