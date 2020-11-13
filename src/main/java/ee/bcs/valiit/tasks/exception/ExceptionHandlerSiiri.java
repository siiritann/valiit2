// see klass otsustab, mis kontrolleri exceptionist

package ee.bcs.valiit.tasks.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerSiiri extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> accountDoesNotExist(ApplicationException e){
        e.printStackTrace();
        System.out.println(e.getMessage()); // message tegime ka dünaamiliseks
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), new HttpHeaders(),
                HttpStatus.valueOf(e.getStatusCode())); // TEGIME HTTP dünaamiliseks, tema versioon oli fixed
//                HttpStatus.I_AM_A_TEAPOT);
    }



    // üks handler siin võiks olla üldine

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleError(Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("Server error siiri"), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
