package com.javacohort3.personapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class RestExpectionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlePersonNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("PersonNotFoundExpection");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
        String requestPath = (String) request.getAttribute("javax.servlet.error. request_uri");
        if(requestPath == null) {
            request.getRequestURI();
        }
        return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleNotReadableMessageExpection(HttpMessageNotReadableException h){
        ErrorDetail errorDetails = new ErrorDetail();
        errorDetails.setTitle("RestExpectionHandler active");
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDetail(h.getMessage());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDeveloperMessage("|||Follow on Github @imowbray97|||");
        errorDetails.setErrors(h.fillInStackTrace());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleRequestMethodNotSupported(HttpRequestMethodNotSupportedException hrmns){
        ErrorDetail errorDetails = new ErrorDetail();
        errorDetails.setTitle("RestExpectionHandler active");
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDetail(hrmns.getMethod() + " || " + hrmns.getMessage());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDeveloperMessage("|||Follow on Github @imowbray97|||");
        errorDetails.setErrors(hrmns.fillInStackTrace());
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<?> handleHttpMessageNotWritableException(TransactionSystemException cve){
        ErrorDetail errorDetails = new ErrorDetail();
        errorDetails.setTitle("TransactionSystemException");
        errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDeveloperMessage(cve.getMessage());
        errorDetails.setErrors(cve.getCause().fillInStackTrace());
        errorDetails.setDetail(cve.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
