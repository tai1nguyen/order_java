package org.nguyen.orderjava.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends Exception {
    
    private static final long serialVersionUID = 2577856845359637170L;

    public OrderNotFoundException(String orderId) {
        super("Order '" + orderId + "' Not Found");
	}
}
