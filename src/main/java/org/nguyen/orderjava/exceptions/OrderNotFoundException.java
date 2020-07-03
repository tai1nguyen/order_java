package org.nguyen.orderjava.exceptions;

public class OrderNotFoundException extends Exception {

    public OrderNotFoundException(String orderId) {
        super("Order '" + orderId + "' Not Found");
	}

	/**
     * Generated serial UID.
     */
    private static final long serialVersionUID = 2577856845359637170L;
}
