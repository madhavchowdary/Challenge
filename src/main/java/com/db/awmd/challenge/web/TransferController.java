package com.db.awmd.challenge.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.db.awmd.challenge.domain.AmountTransfer;
import com.db.awmd.challenge.exception.AmountTransferException;
import com.db.awmd.challenge.service.TransferService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author madhav
 *
 */
@RestController
@RequestMapping("/v1/transfer")
@Slf4j
public class TransferController {

	private final TransferService transferService;
	  
	  private static final Logger logger = LoggerFactory. getLogger(TransferController. class);

	  /**
	 * @param transferService
	 */
	@Autowired
	  public TransferController(TransferService transferService) {
	    this.transferService = transferService;
	  }
	  
	  /**
	 * @param amountTransfer
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> transferAmount(@RequestBody @Validated AmountTransfer amountTransfer) {
		  try {
			  
			  transferService.amountTransfer(amountTransfer.getAccountFrom(), amountTransfer.getAccountTo(), amountTransfer.getTransferAmount());
			  logger.info("amount transfer request object", amountTransfer);
				
			} catch (AmountTransferException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			  return new ResponseEntity<>("Transfer Completed",HttpStatus.ACCEPTED);
		  }
}
