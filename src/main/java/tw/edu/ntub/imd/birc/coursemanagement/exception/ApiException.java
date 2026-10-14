package tw.edu.ntub.imd.birc.coursemanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String errorCode;

<<<<<<< HEAD
    public ApiException(HttpStatus status, String errorCode, String message) {
=======
    public ApiException(HttpStatus status, String errorCode,String message) {
>>>>>>> 816cc145ffa90a412a776ce5140ed08304a74382
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 816cc145ffa90a412a776ce5140ed08304a74382
