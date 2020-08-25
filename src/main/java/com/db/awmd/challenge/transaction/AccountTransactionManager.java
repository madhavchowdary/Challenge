/*
 * package com.db.awmd.challenge.transaction;
 * 
 * import java.lang.reflect.Proxy; import java.util.List; import java.util.Map;
 * 
 * import org.springframework.stereotype.Service;
 * 
 * import com.db.awmd.challenge.domain.Account; import
 * com.db.awmd.challenge.repository.AccountsRepository; import
 * com.db.awmd.challenge.repository.TransferRepository;
 * 
 * import lombok.Getter;
 * 
 *//**
	 * @author This class Handles Transactional Operations
	 *
	 *//*
		 * @Service public class AccountTransactionManager {
		 * 
		 * private final TransferRepository transferRepository;
		 * 
		 * private TransacrionInvocationHandler<Account> handler;
		 * 
		 * @Getter private boolean autoCommit = false;
		 * 
		 * @Getter private TransferRepository repoProxy;
		 * 
		 * public AccountTransactionManager(TransferRepository repository){
		 * this.transferRepository = repository;
		 * 
		 * handler = new TransacrionInvocationHandler<Account>(transferRepository);
		 * repoProxy =
		 * (TransferRepository)Proxy.newProxyInstance(TransferRepository.class.
		 * getClassLoader() , new Class[] { TransferRepository.class }, handler);
		 * 
		 * }
		 * 
		 * public boolean doInTransaction(TransactionCallback callback) { boolean
		 * txStatus = false; TransactionContext<Account, Account> context = new
		 * TransactionContext<>(); ThreadLocal<TransactionContext<Account, Account>>
		 * localContext = handler.getLocalContext(); localContext.set(context); try {
		 * callback.process(); if(autoCommit) { txStatus = true; } }catch(Exception e) {
		 * rollBack(); txStatus = false; throw e; }finally {
		 * 
		 * }
		 * 
		 * return txStatus; }
		 * 
		 * public void commit() { TransactionContext<Account, Account> localContext =
		 * handler.getLocalContext().get(); Map<Account, Account> savePoints =
		 * localContext.getSavePoints(); // swap save points value to repository
		 * savePoints.entrySet().forEach(entry -> { Account key = entry.getKey();
		 * Account value = entry.getValue(); value.setBalance(key.getBalance()); });
		 * 
		 * }
		 * 
		 * public void rollBack() { // Destroy Save points within same transactional
		 * context TransactionContext<Account, Account> localContext =
		 * handler.getLocalContext().get(); localContext.getSavePoints().clear(); }
		 * 
		 * }
		 */