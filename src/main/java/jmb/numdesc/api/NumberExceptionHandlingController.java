/*
 * James Brundege
 * Date: 2018-01-10
 * MIT license: https://opensource.org/licenses/MIT
 */
package jmb.numdesc.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles Exceptions for the REST API.
 */
@ControllerAdvice
public class NumberExceptionHandlingController {

    private static final String INVALID_NUMBER_TEMPLATE = "Invalid number: [%s]. Number must be an integer between " +
            Integer.MIN_VALUE + " and " + Integer.MAX_VALUE + " inclusive";

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse invalidNumber(HttpServletRequest request) {
        String parameter = request.getRequestURI().replace(NumberDescriberController.PATH, "");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorMessage(String.format(INVALID_NUMBER_TEMPLATE, parameter));
        return errorResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse notFound() {
        return new ErrorResponse(HttpStatus.NOT_FOUND);
    }

}
