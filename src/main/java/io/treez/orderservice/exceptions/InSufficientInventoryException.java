package io.treez.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "One or more item's quantity have exceeded the inventory")
public class InSufficientInventoryException extends RuntimeException {

}
