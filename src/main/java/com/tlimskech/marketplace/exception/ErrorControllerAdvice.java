package com.tlimskech.marketplace.exception;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
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
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> handlePasswordUnMatchException(HttpServletRequest request, DataNotFoundException exception) {
        ErrorData data = new ErrorData(ErrorCode.DATA_NOT_FOUND, exception.getMessage());
        HttpStatus status = getStatus(request);
        exception.printStackTrace();
        return ResponseEntity.status(status).body(data);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxUploadSizeExceededException(HttpServletRequest request, MaxUploadSizeExceededException exception) {
        exception.printStackTrace();
        ErrorData data = new ErrorData(ErrorCode.MAX_FILE_LIMIT, exception.getMessage());
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(data);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> handleMultipartException(HttpServletRequest request, MultipartException exception) {
        exception.printStackTrace();
        ErrorData data = new ErrorData(ErrorCode.MAX_FILE_LIMIT, exception.getMessage());
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(data);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlePasswordUnMatchException(HttpServletRequest request, NumberFormatException exception) {
        ErrorData data = new ErrorData(ErrorCode.BAD_ARGUEMENT, "Invalid Parameter passed. Please enter a number");
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(data);
    }

    @ExceptionHandler(SQLGrammarException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<?> handleUserNameNotFoundException(HttpServletRequest request, SQLGrammarException exception) {
        ErrorData data = new ErrorData(ErrorCode.SQL_GRAMMAR, exception.getMessage());
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(data);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleUserNameNotFoundException(HttpServletRequest request, UsernameNotFoundException exception) {
        ErrorData data = new ErrorData(ErrorCode.DATA_NOT_FOUND, exception.getMessage());
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(data);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleUserNameNotFoundException(HttpServletRequest request, BadCredentialsException exception) {
        ErrorData data = new ErrorData(ErrorCode.AUTHENTICATION, exception.getMessage());
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(data);
    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleUserNameNotFoundException(HttpServletRequest request, org.hibernate.exception.ConstraintViolationException exception) {
        exception.printStackTrace();
        List<ErrorData> errors = new ArrayList<>();
//        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
//            String value = (violation.getInvalidValue() == null ? null : violation.getInvalidValue().toString());
//            errors.add(new ErrorData(violation.getPropertyPath().toString(), value, violation.getMessage()));
//        }
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        List<ErrorData> errors = new ArrayList<>();
        ErrorData data = new ErrorData(exception.getClass().getName(), exception.getLocalizedMessage(),
                exception.getMessage());
        errors.add(data);
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(errors);
    }

    /**
     * Exception handler for validation errors caused by method
     * parameters @RequesParam, @PathVariable, @RequestHeader annotated with
     * javax.validation constraints.
     */

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException exception) {
        exception.printStackTrace();
        List<ErrorData> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String value = (violation.getInvalidValue() == null ? null : violation.getInvalidValue().toString());
            errors.add(new ErrorData(violation.getPropertyPath().toString(), value, violation.getMessage()));
        }
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(errors);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(HttpServletRequest request, BindException exception) {
        HttpStatus status = getStatus(request);
        exception.printStackTrace();
        return ResponseEntity.status(status).body(convert(exception.getAllErrors()));
    }

    /**
     * Exception handler for @RequestBody validation errors.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        HttpStatus status = getStatus(request);
        exception.printStackTrace();
        return ResponseEntity.status(status).body(convert(exception.getBindingResult().getAllErrors()));
    }

    /**
     * Exception handler for missing required parameters errors.
     */

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<?> handleServletRequestBindingException(HttpServletRequest request, ServletRequestBindingException exception) {
        HttpStatus status = getStatus(request);
        exception.printStackTrace();
        return ResponseEntity.status(status).body(new ErrorData(null, null, exception.getMessage()));
    }

    /**
     * Exception handler for invalid payload (e.g. json invalid format error).
     */

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        HttpStatus status = getStatus(request);
        exception.printStackTrace();
        return ResponseEntity.status(status).body(new ErrorData(null, null, exception.getMessage()));
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

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
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
