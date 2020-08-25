package com.db.awmd.challenge.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.mapper.AccountRowMapper;

/**
 * @author madhav
 *
 */
@Repository
public class TransferRepositoryImpl implements TransferRepository {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	private final Map<String, Account> accounts = new ConcurrentHashMap<>();

	  /**
	 * Create Account
	 */
	@Override
	  public void createAccount(Account account) throws DuplicateAccountIdException {
	    Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
	    if (previousAccount != null) {
	      throw new DuplicateAccountIdException(
	        "Account id " + account.getAccountId() + " already exists!");
	    }
	  } 

	  /**
	 * returns Account
	 */ 
	@Override
	  public Account findById(String accountId) {
		try {
			 String sql = "SELECT * FROM ACCOUNTS WHERE ID=?";
			    return (Account) jdbcTemplate.queryForObject(sql, new Object[] {accountId}, new AccountRowMapper());
		}catch(EmptyResultDataAccessException emp)  {
			return null;
		}
	  }
	
	@Override
	public boolean update(String fromAccount, BigDecimal transferAmount) {
		
		  int result = jdbcTemplate.update("UPDATE ACCOUNTS " + "SET BALANCE=? " + "WHERE ID = ?", new Object[] {transferAmount, fromAccount});
		
		  if(result == 0) {
			  return false;
		  } else {
			  return true;
		  }
	}

}
