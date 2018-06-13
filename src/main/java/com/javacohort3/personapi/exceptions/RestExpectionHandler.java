package com.javacohort3.personapi.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.javacohort3.personapi.error_detail.ErrorDetail;
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
    public ResponseEntity<?> handlePersonNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest req){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Person Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
        String requestPath = (String) req.getAttribute("javax.servlet.error. request_uri");
        if(requestPath == null) {
            req.getRequestURI();
        }
        return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleNotReadableMessageExpection(HttpMessageNotReadableException hmnre){
        ErrorDetail errorDetails = new ErrorDetail();
        errorDetails.setTitle("RestExpectionHandler active");
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDetail(hmnre.getMessage());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDeveloperMessage(hmnre.getLocalizedMessage());
        errorDetails.setErrors(hmnre.fillInStackTrace());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleRequestMethodNotSupported(HttpRequestMethodNotSupportedException hrmnse){
        ErrorDetail errorDetails = new ErrorDetail();
        errorDetails.setTitle("Request Method Not Supported");
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDetail(hrmnse.getMethod() + " || " + hrmnse.getMessage());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDeveloperMessage(hrmnse.getLocalizedMessage());
        errorDetails.setErrors(hrmnse.fillInStackTrace());
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<?> handleHttpMessageNotWritableException(TransactionSystemException tse){
        ErrorDetail errorDetails = new ErrorDetail();
        errorDetails.setTitle("TransactionSystemException");
        errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDeveloperMessage(tse.getMessage());
        errorDetails.setErrors(tse.getCause().fillInStackTrace());
        errorDetails.setDetail(tse.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<?> handleJsonParseException(JsonParseException jpe) {
        ErrorDetail errorDetails = new ErrorDetail();
        errorDetails.setTitle("JSONParseException");
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDeveloperMessage(jpe.getMessage());
        errorDetails.setErrors(jpe.getCause().fillInStackTrace());
        errorDetails.setDetail(jpe.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
