package edu.uw.rgm.account;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.dao.AccountDao;
import edu.uw.rgm.dao.AccountSer;
import edu.uw.rgm.dao.FileAccountDao;

public class SimpleAccountManagerTest extends test.AccountManagerTest{

	@Test
	public void testSimpleAccountManager() {
		InputStream accountIn = null;
		SimpleAccount accountAfterSer = null;
		try {
			accountIn=new FileInputStream("account.dat");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			accountAfterSer = (SimpleAccount) AccountSer.read(accountIn);
		} catch (AccountException e1) {
			e1.printStackTrace();
		}
		AccountDao dao1 = null;
		try {
			dao1 = new FileAccountDao();
			dao1.setAccount(accountAfterSer);
//			dao1.getAccount("my_bank_account");
			dao1.close();
		} catch (AccountException e1) {
			e1.printStackTrace();
		}
		
		SimpleAccountManager simpleAcct = new SimpleAccountManager(dao1);
		try {
			SimpleAccount acct = (SimpleAccount) simpleAcct.getAccount("simple_Account");
			System.out.println(acct.getBalance());
			assertTrue(acct.getBalance()==300000);
		} catch (AccountException e) {
			e.printStackTrace();
		}
		
		
//		fail("Not yet implemented");
	}

	@Test
	public void testPersist() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetAccount() {
		
		
//		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAccount() {
//		InputStream accountIn = null;
//		SimpleAccount accountAfterSer = null;
//		try {
//			accountIn=new FileInputStream("account.dat");
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		try {
//			accountAfterSer = (SimpleAccount) AccountSer.read(accountIn);
//		} catch (AccountException e1) {
//			e1.printStackTrace();
//		}
//		AccountDao dao1 = null;
//		try {
//			dao1 = new FileAccountDao();
//			dao1.setAccount(accountAfterSer);
//			dao1.close();
//		} catch (AccountException e1) {
//			e1.printStackTrace();
//		}
//		
//		SimpleAccountManager simpleAcct = new SimpleAccountManager(dao1);
//		try {
//			simpleAcct.deleteAccount("Account1233");
//			SimpleAccount myAcct=(SimpleAccount) dao1.getAccount("Account1233");
//			System.out.println(myAcct.getBalance());
//		} catch (AccountException e) {
//			e.printStackTrace();
//		}
//		try {
//			assertTrue(simpleAcct.getAccount("Account1233")==null);
//		} catch (AccountException e) {
//			e.printStackTrace();
//		}
//		fail("Not yet implemented");
	}

	@Test
	public void testCreateAccount() {
		InputStream accountIn = null;
		SimpleAccount accountAfterSer = null;
		try {
			accountIn=new FileInputStream("account.dat");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			accountAfterSer = (SimpleAccount) AccountSer.read(accountIn);
		} catch (AccountException e1) {
			e1.printStackTrace();
		}
		AccountDao dao1 = null;
		try {
			dao1 = new FileAccountDao();
			dao1.setAccount(accountAfterSer);
//			dao1.getAccount("my_bank_account");
			dao1.close();
		} catch (AccountException e1) {
			e1.printStackTrace();
		}
		
		SimpleAccountManager simpleAcct = new SimpleAccountManager(dao1);
		try {
			simpleAcct.createAccount("simple_Account12", "password", 300000);
			SimpleAccount myAcct=(SimpleAccount) dao1.getAccount("simple_Account12");
			System.out.println(myAcct.getBalance());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		try {
			assertTrue(simpleAcct.getAccount("simple_Account12").getEmail()==null);
		} catch (AccountException e) {
			e.printStackTrace();
		}
//		fail("Not yet implemented");
	}

	@Test
	public void testValidateLogin() {
//		fail("Not yet implemented");
	}

	@Test
	public void testClose() {
//		fail("Not yet implemented");
	}

}
