package com.db.awmd.challenge.domain;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author madhav
 *
 */

@Data
public class AmountTransfer {
	
	@NotNull
	private String accountFrom;
	
	@NotNull
	private String accountTo;
	
	@NotNull
	@Min(value = 0, message = "Initial balance must be positive.")
	private BigDecimal transferAmount;

}
