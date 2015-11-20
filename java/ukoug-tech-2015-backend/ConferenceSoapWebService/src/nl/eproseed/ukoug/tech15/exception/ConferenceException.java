package nl.eproseed.ukoug.tech15.exception;


public class ConferenceException extends RuntimeException {
    
    private static final long serialVersionUID = -2853934577706819646L;

    public ConferenceException(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public ConferenceException(Throwable throwable) {
        super(throwable);
    }

    public ConferenceException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ConferenceException(String string) {
        super(string);
    }

    public ConferenceException() {
        super();
    }
}
