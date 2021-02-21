package edu.uw.rgm.account;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.Test;

import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;
import edu.uw.ext.framework.order.Order;

public class SimpleAccountTest extends test.AccountTest{
	
	byte [] password = {1, 2,3};
	
	@Test
	public void testSimpleAccount() {
	
	try {
		SimpleAccount acc = new SimpleAccount("newAccount",password,110000);
//		System.out.println(acc.getName());
	}catch(final AccountException e) {
		System.out.println("Unable to create account");
	}
//		fail("Not yet implemented");
	}

	@Test
	public void testSimpleAccountStringByteArrayInt() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String accName = acct.getName();
		assertEquals(accName, "newAccount");
//		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			acct.setName("bankAccount");
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String accName = acct.getName();
		assertEquals(accName, "bankAccount");
	}

	@Test
	public void testGetPasswordHash() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
//			System.out.println(acct.getPasswordHash().length);
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertEquals(acct.getPasswordHash().length,3);
//		fail("Not yet implemented");
	}

	@Test
	public void testSetPasswordHash() {
		byte[] pass= {3, 4, 5, 6};
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
			acct.setPasswordHash(pass);
//			System.out.println(acct.getPasswordHash().length);
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertEquals(acct.getPasswordHash().length,4);
//		fail("Not yet implemented");
	}

	@Test
	public void testGetBalance() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
//			acct.setBalance(10000);
//			System.out.println(acct.getCreditCard());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertEquals(acct.getBalance(),110000);
//		fail("Not yet implemented");
	}

	@Test
	public void testSetBalance() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
			acct.setBalance(5000);
//			System.out.println(acct.getCreditCard());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		int balance = acct.getBalance();
//		System.out.println(balance);
		assertTrue(acct.getBalance()==5000);
//		fail("Not yet implemented");
	}

	@Test
	public void testGetFullName() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		acct.setFullName("New_Bank_Account");
		String fullName = acct.getFullName();
//		System.out.println(fullName);
		assertEquals(fullName,"New_Bank_Account");
//		fail("Not yet implemented");
	}

	@Test
	public void testSetFullName() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		acct.setFullName("New_Bank_Account");
		String fullName = acct.getFullName();
//		System.out.println(fullName);
		assertEquals(fullName,"New_Bank_Account");
//		fail("Not yet implemented");
	}

	@SuppressWarnings("null")
	@Test
	public void testGetAddress() {
	SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
//			System.out.println(acct.getAddress());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertTrue(acct.getAddress()==null);
		
//		fail("Not yet implemented");
	}

	@Test
	public void testSetAddress() {
//		SimpleAddress addr = null;
//		addr.setCity("Seattle");
//		System.out.println(addr.getCity());
//		fail("Not yet implemented");
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
//			System.out.println(acct.getAddress());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertTrue(acct.getAddress()==null);
		
	}

	@Test
	public void testGetPhone() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
			acct.setPhone("2057077717");
//			System.out.println(acct.getAddress());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertTrue(acct.getPhone().equals("2057077717"));
		
//		fail("Not yet implemented");
	}

	@Test
	public void testSetPhone() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
			acct.setPhone("2057077717");
//			System.out.println(acct.getAddress());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		String phone =acct.getPhone();
		assertEquals(phone,"2057077717");
//		fail("Not yet implemented");
	}

	@Test
	public void testGetEmail() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
			acct.setEmail("email@gmail.com");
//			System.out.println(acct.getAddress());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertTrue(acct.getEmail().equals("email@gmail.com"));
//		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
			acct.setEmail("email@gmail.com");
//			System.out.println(acct.getAddress());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertTrue(acct.getEmail().equals("email@gmail.com"));
//		fail("Not yet implemented");
	}

	@Test
	public void testGetCreditCard() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
//			CreditCard card = null;
//			card.setExpirationDate("2025");
//			acct.setCreditCard(card);
//			System.out.println(acct.getCreditCard());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertTrue(acct.getCreditCard()==null);
//		fail("Not yet implemented");
	}

	@Test
	public void testSetCreditCard() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
//			CreditCard card = null;
//			card.setExpirationDate("2025");
//			acct.setCreditCard(card);
//			System.out.println(acct.getCreditCard());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertTrue(acct.getCreditCard()==null);
//		fail("Not yet implemented");
	}

	@Test
	public void testRegisterAccountManager() {
		 Optional<AccountManager> acctMngr = Optional.empty();
//		 System.out.println(acctMngr.toString());
		 assertTrue(acctMngr.isEmpty());
		 
//		fail("Not yet implemented");
	}

	@Test
	public void testReflectOrder() {
		SimpleAccount acct = null;
		try {
			acct = new SimpleAccount("newAccount",password,110000);
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertTrue(acct.getBalance()==110000);
		
		
//		fail("Not yet implemented");
	}

}
