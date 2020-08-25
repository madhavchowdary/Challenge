package com.db.awmd.challenge.repository;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
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
	    String sql = "SELECT * FROM ACCOUNTS WHERE ID=?";
	    return (Account) jdbcTemplate.queryForObject(sql, new Object[] {accountId}, new AccountRowMapper());
	  }
	
	@Override
	public boolean updateById(String fromAccount, BigDecimal transferAmount) {
		
		String sql = "UPDATE ACCOUNTS SET BALANCE=? WHERE ID = ?";
		  Object[] params = { transferAmount, fromAccount };
		  int[] types = {Types.DECIMAL, Types.INTEGER};
		  int result = jdbcTemplate.update(sql, params, types);
		
		  if(result == 0) {
			  return false;
		  } else {
			  return true;
		  }
		
	}

}
