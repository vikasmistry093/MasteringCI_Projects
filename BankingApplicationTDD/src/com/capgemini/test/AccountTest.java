package com.capgemini.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
public class AccountTest {

	AccountService accountService ;
	
	@Mock
	AccountRepository accountRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*  create account
	 *  1.when the amount is less than 500 system should throw exception
	 *  2.when the valid info is passed account should be created successfully
	 */
	
	
	@Test(expected = com.capgemini.exceptions.InsufficientInitialBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialBalanceException
	{
		accountService.createAccount(101, 200);
	}
	
	
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialBalanceException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepository.save(account)).thenReturn(true);
		
		assertEquals(account,accountService.createAccount(101, 5000));
		
	}
	
	@Test
	public void showBalanceforAccountNumber() throws InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepository.searchAccount(account.getAccountNumber())).thenReturn(account);
		
		assertEquals(account.getAmount(), accountService.showBalance(101));
	}
	
	@Test
	public void depositAmountforAccountNumber() throws InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepository.searchAccount(account.getAccountNumber())).thenReturn(account);
		
		assertEquals(7000, accountService.depositAmount(101, 2000));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
