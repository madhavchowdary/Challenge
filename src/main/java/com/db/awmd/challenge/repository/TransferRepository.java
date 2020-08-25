package com.db.awmd.challenge.repository;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;

@Repository
public interface TransferRepository {
	
	public void createAccount(Account account) throws DuplicateAccountIdException;
	public Account findById(String accountId);
	public boolean update(String fromAccount, BigDecimal transferAmount);
	
}