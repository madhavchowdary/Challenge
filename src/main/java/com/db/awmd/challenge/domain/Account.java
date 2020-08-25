package com.db.awmd.challenge.domain;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Account {

  @NotNull
  private String accountId;
  
  @NotNull
  @Min(value = 0, message = "Initial balance must be positive.")
  private BigDecimal balance;

}