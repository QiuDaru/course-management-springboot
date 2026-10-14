package tw.edu.ntub.imd.birc.coursemanagement.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tw.edu.ntub.imd.birc.coursemanagement.exception.ApiException;
import tw.edu.ntub.imd.birc.coursemanagement.util.http.ResponseEntityBuilder;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler {

    // 1) 業務自訂例外：例外自己帶著 status / errorCode / message，這裡只負責拆開回傳
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e) {
        return ResponseEntityBuilder.error()
                .status(e.getStatus())
                .errorCode(e.getErrorCode())
                .message(e.getMessage())
                .build();
    }

    // 2) @Valid 欄位驗證失敗：Spring 會丟 MethodArgumentNotValidException，統一轉成 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .orElse(null);

        String field = fieldError != null ? fieldError.getField() : "";
        String code = fieldError != null ? fieldError.getCode() : "";
        String message;

        switch (code) {
            case "NotBlank":
            case "NotNull":
                message = "缺少必要欄位：" + field;
                break;
            case "Email":
                message = "信箱格式錯誤";
                break;
            case "Min":
            case "PositiveOrZero":
                message = "credit 不可為負數";
                break;
            default:
                message = fieldError != null ? fieldError.getDefaultMessage() : "欄位驗證失敗";
        }

        return ResponseEntityBuilder.error()
                .status(HttpStatus.BAD_REQUEST)
                .errorCode("FormValidation - Invalid")
                .message(message)
                .build();
    }
}
