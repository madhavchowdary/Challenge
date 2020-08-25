package com.db.awmd.challenge.exception;

/**
 * @author madhav
 *
 */
public class AmountTransferException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public AmountTransferException(String message) {
	   super(message);
	}
}