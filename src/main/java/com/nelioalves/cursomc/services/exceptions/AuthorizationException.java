package com.nelioalves.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException {
	    /** default serialVersionUID */
	    private static final long serialVersionUID = 1L;

	    public AuthorizationException(String msg) {
	        super(msg);
	    }

	    public AuthorizationException(String msg, Throwable e) {
	        super(msg,e);
	    }

}
