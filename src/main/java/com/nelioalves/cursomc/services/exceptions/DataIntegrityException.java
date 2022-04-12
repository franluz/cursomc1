package com.nelioalves.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {
	    /** default serialVersionUID */
	    private static final long serialVersionUID = 1L;

	    public DataIntegrityException(String msg) {
	        super(msg);
	    }

	    public DataIntegrityException(String msg, Throwable e) {
	        super(msg,e);
	    }

}
