package com.db.awmd.challenge.transaction;

/**
 * @author madhav
 *
 */
@FunctionalInterface
public interface TransactionCallback {
	
	public void process();
}
