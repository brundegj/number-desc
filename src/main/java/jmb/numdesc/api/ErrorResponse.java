/*
 * James Brundege
 * Date: 2018-01-10
 * MIT license: https://opensource.org/licenses/MIT
 */
package jmb.numdesc.api;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Data structure for error responses returned from the REST API.
 */
@Data
public class ErrorResponse {

    private int errorCode;
    private String errorReason;
    private String errorMessage;

    public ErrorResponse(HttpStatus httpStatus) {
        errorCode = httpStatus.value();
        errorReason = httpStatus.getReasonPhrase();
    }
}
