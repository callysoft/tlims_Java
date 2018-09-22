package com.tlimskech.marketplace.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorControllerAdvice {

    private final MessageSource messageSource;

    @Autowired
    public ErrorControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /*@ExceptionHandler(PasswordUnMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlePasswordUnMatchException(PasswordUnMatchException exception) {
        ErrorData data = new ErrorData(exception.getMessage());
        System.out.println("PasswordUnMatchException");
        return ResponseEntity.ok(data);
    }*/

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handlePasswordUnMatchException(DataNotFoundException exception) {
        ErrorData data = new ErrorData(ErrorCode.DATA_NOT_FOUND, exception.getMessage());
        return ResponseEntity.ok(data);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlePasswordUnMatchException(NumberFormatException exception) {
        ErrorData data = new ErrorData(ErrorCode.BAD_ARGUEMENT, "Invalid Parameter passed. Please enter a number");
        return ResponseEntity.ok(data);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleUserNameNotFoundException(UsernameNotFoundException exception) {
        ErrorData data = new ErrorData(ErrorCode.DATA_NOT_FOUND, exception.getMessage());
        return ResponseEntity.ok(data);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleUserNameNotFoundException(BadCredentialsException exception) {
        ErrorData data = new ErrorData(ErrorCode.AUTHENTICATION, exception.getMessage());
        return ResponseEntity.ok(data);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintException(Exception exception) {
        exception.printStackTrace();
        List<ErrorData> errors = new ArrayList<>();
        ErrorData data = new ErrorData(exception.getClass().getName(), exception.getLocalizedMessage(),
                exception.getMessage());
        errors.add(data);
        System.out.println(errors.get(0));
        System.out.println("Error occured here");
        return ResponseEntity.ok(errors);
    }

    /**
     * Exception handler for validation errors caused by method
     * parameters @RequesParam, @PathVariable, @RequestHeader annotated with
     * javax.validation constraints.
     */

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ErrorData> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String value = (violation.getInvalidValue() == null ? null : violation.getInvalidValue().toString());
            errors.add(new ErrorData(violation.getPropertyPath().toString(), value, violation.getMessage()));
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException exception) {
        return ResponseEntity.badRequest().body(convert(exception.getAllErrors()));
    }

    /**
     * Exception handler for @RequestBody validation errors.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(convert(exception.getBindingResult().getAllErrors()));
    }

    /**
     * Exception handler for missing required parameters errors.
     */

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<?> handleServletRequestBindingException(ServletRequestBindingException exception) {
        return ResponseEntity.badRequest().body(new ErrorData(null, null, exception.getMessage()));
    }

    /**
     * Exception handler for invalid payload (e.g. json invalid format error).
     */

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body(new ErrorData(null, null, exception.getMessage()));
    }

    protected List<ErrorData> convert(List<ObjectError> objectErrors) {
        List<ErrorData> errors = new ArrayList<>();
        for (ObjectError objectError : objectErrors) {
            String message = objectError.getDefaultMessage();
            if (message == null) {
                // when using custom spring validator //
                // org.springframework.validation.Validator need to resolve
                // messages manually
                message = messageSource.getMessage(objectError, null);
            }

            ErrorData error;
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                String value = (fieldError.getRejectedValue() == null ? null
                        : fieldError.getRejectedValue().toString());
                error = new ErrorData(fieldError.getField(), value, message);
            } else error = new ErrorData(objectError.getObjectName(), objectError.getCode(),
                    objectError.getDefaultMessage());
            errors.add(error);
        }
        return errors;
    }
    /*
    @ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  public MessageDTO processValidationError(MethodArgumentNotValidException ex) {
	    BindingResult result = ex.getBindingResult();
	    FieldError error = result.getFieldError();

	    return processFieldError(error);
	  }

	  private MessageDTO processFieldError(FieldError error) {
	    MessageDTO message = null;
	    if (error != null) {
	      Locale currentLocale = LocaleContextHolder.getLocale();
	      String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
	      message = new MessageDTO(MessageType.ERROR, msg);
	    }
	    return message;
	  }*/
}
