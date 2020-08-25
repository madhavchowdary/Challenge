package com.db.awmd.challenge.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.db.awmd.challenge.domain.Account;

public class AccountRowMapper implements RowMapper < Account > {

	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		
			Account account = new Account(); 
	    	account.setAccountId(rs.getString("ID"));
	    	account.setBalance(BigDecimal.valueOf(Long.valueOf(rs.getString("BALANCE"))));
	        return account;
    }
}
