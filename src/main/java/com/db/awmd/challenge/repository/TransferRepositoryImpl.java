package com.db.awmd.challenge.repository;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.mapper.AccountRowMapper;

/**
 * @author madhav
 *
 */
@Repository
public class TransferRepositoryImpl implements TransferRepository {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
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
