package com.db.awmd.challenge.repository;

import java.math.BigDecimal;
import org.springframework.stereotype.Repository;
import com.db.awmd.challenge.domain.Account;

@Repository
public interface TransferRepository {
	
	public Account findById(String accountId);
	public boolean update(String fromAccount, BigDecimal transferAmount);
	
}