package ee.bcs.valiit.tasks.exception;

public class ApplicationException extends RuntimeException{
    private int statusCode = 400; // SIIA PANIME DEFAULT VÄÄRTUSE
    public ApplicationException(String message){
        super(message); // TODO MIS SEE SUPER TEEB?
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApplicationException() {
    }

    public ApplicationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
