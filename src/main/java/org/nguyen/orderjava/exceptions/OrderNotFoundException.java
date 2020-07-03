package org.nguyen.orderjava.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Not Found")
public class OrderNotFoundException extends Exception {

    public OrderNotFoundException(String orderId) {
        super("Order '" + orderId + "' Not Found");
	}

	/**
     * Generated serial UID.
     */
    private static final long serialVersionUID = 2577856845359637170L;
}
