package com.db.awmd.challenge.service;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.AmountTransferException;
import com.db.awmd.challenge.repository.TransferRepository;

/**
 * @author madhav
 *
 */
@Service
public class TransferService {
	
	private static final Logger logger = LoggerFactory. getLogger(TransferService. class);
	
	 TransferRepository transferRepository;
	
	/**
	 * @param transferRepository
	 */
	@Autowired
	  public TransferService(TransferRepository transferRepository) {
	    this.transferRepository = transferRepository;
	  }

	/**
	 * @param fromAccount
	 * @param toAccount
	 * @param transferAmount
	 * @throws AmountTransferException
	 */
	public void amountTransfer(String fromAccount, String toAccount, BigDecimal transferAmount)
			throws AmountTransferException {

		Account accountFrom = transferRepository.findById(fromAccount);
		Account accountTo = transferRepository.findById(toAccount);

		if (accountFrom != null) {
			int res = accountFrom.getBalance().compareTo(transferAmount);
			if (res == 1) {
				if (accountTo != null) {
					
					final ReadWriteLock rwl = new ReentrantReadWriteLock();

					rwl.readLock().lock();

					try {

						logger.info("success path scenario ");
						BigDecimal debitAmt = accountFrom.getBalance().subtract(transferAmount);
						BigDecimal creditAmt = accountTo.getBalance().add(transferAmount);
						boolean fromact = update(fromAccount, debitAmt);
						boolean toact = update(toAccount, creditAmt);

						if (fromact && toact) {
							NotificationService notificationService = new EmailNotificationService();

							String transferDescription = transferAmount + "debited from your account";
							notificationService.notifyAboutTransfer(accountFrom, transferDescription);

							String transferDescr = transferAmount + "credited to your account";
							notificationService.notifyAboutTransfer(accountTo, transferDescr);
						}

					} finally {
						rwl.readLock().unlock();
					}

				} else {
					throw new AmountTransferException("To Account Number does not exist");
				}

			} else {
				throw new AmountTransferException("Not having enough balance from account");
			}
		} else {
			throw new AmountTransferException("From Account Number does not exist");
		}

	}
	
	public boolean update(String fromAccount, BigDecimal transferAmount){
		return transferRepository.update(fromAccount, transferAmount);
	}
}
