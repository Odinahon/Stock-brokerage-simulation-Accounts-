package edu.uw.rgm.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.uw.ext.framework.account.AccountException;
import edu.uw.rgm.account.SimpleAccount;

public class FileAccountDaoTest extends test.DaoTest{

	@Test
	public void testGetAccount() {
		FileAccountDao dao =null;
		dao= new FileAccountDao();
		dao.getAccount("simple_Account");
		try {
			dao.close();
		} catch (AccountException e) {
			e.printStackTrace();
		}
//		System.out.println(dao.getAccount("simple_Account").getBalance());
		assertTrue(dao.getAccount("simple_Account").getBalance()==300000);
		
//		fail("Not yet implemented");
	}

	@Test
	public void testSetAccount() {
		SimpleAccount acct = null;
		byte[]password = {2,2,2,2,2};
		try {
			acct = new SimpleAccount("newAccount",password,110000);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileAccountDao dao =null;
		dao= new FileAccountDao();
		try {
			dao.setAccount(acct);
		} catch (AccountException e1) {
			e1.printStackTrace();
		}
		try {
			dao.close();
		} catch (AccountException e) {
			e.printStackTrace();
		}
//		System.out.println(dao.getAccount("newAccount").getBalance());
		assertTrue(dao.getAccount("newAccount").getBalance()==110000);
//		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAccount() {
		SimpleAccount acct = null;
		byte[]password = {2,2,2,2,2};
		try {
			acct = new SimpleAccount("newAccount",password,110000);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileAccountDao dao =null;
		dao= new FileAccountDao();
		try {
			dao.setAccount(acct);
			dao.deleteAccount("newAccount");
		} catch (AccountException e1) {
			e1.printStackTrace();
		}
		try {
			dao.close();
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertTrue(dao.getAccount("newAccount")==null);
//		fail("Not yet implemented");
	}

}
