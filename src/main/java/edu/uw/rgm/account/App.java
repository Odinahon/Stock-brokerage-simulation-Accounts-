package edu.uw.rgm.account;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.dao.AccountDao;
import edu.uw.rgm.dao.AccountSer;
import edu.uw.rgm.dao.AddressSer;
import edu.uw.rgm.dao.CreditCardSer;

/**
 * Hello world!
 *
 */
public class App 
{

	public static void main( String[] args )
    {
        System.out.println( "Hello World! Hello There" );
       //create three files Account, Address, CredirCard
        //create these files into parent folder target
//        create file account_fileName
        
        SimpleAddress addr=new SimpleAddress();
        addr.setStreetAddress("Place Street");
        addr.setCity("Seattle");
        addr.setState("WA");
        addr.setZipCode("98059");
        
        
        
        byte [] pass = {1,1,1,1};
        SimpleAccount account = null;
		try {
			account = new SimpleAccount();
			account.setBalance(200000);
			account.setEmail("email@gmail.com");
			account.setFullName("Komiljonov");
			account.setName("my_bank_account");
			account.setPasswordHash(pass);
			account.setPhone("2097077717");
//			account.setAddress(addr);
		} catch (AccountException e3) {
			e3.printStackTrace();
		}
		
        SimpleCreditCard cc = new SimpleCreditCard();
        cc.setIssuer("BankOfAmecira");
        cc.setAccountNumber("123456789");
        cc.setHolder("Holder");
        cc.setType("Vise");
        cc.setExpirationDate("2025");
//        account.setCreditCard(cc);
        OutputStream accountOut;
        OutputStream addressOut;
        OutputStream ccOut;
//        AccountSer accountSer;
        InputStream accountIn = null;
        InputStream addressIn = null;
        InputStream ccIn = null;
        try {
        	accountOut = new FileOutputStream("account.dat");
        	addressOut = new FileOutputStream("address.properties");
        	ccOut = new FileOutputStream("creditcard.txt");
			AccountSer.write(accountOut, account);
			AddressSer.write(addressOut, addr);
			CreditCardSer.write(ccOut, cc);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SimpleAccount accountAfterSer = null;
        SimpleAddress addressAfterSer =null;
        SimpleCreditCard ccAfterSer = null;
		try {
			accountIn=new FileInputStream("account.dat");
			addressIn = new FileInputStream("address.properties");
			ccIn = new FileInputStream("creditcard.txt");
			accountAfterSer = (SimpleAccount) AccountSer.read(accountIn);
			addressAfterSer = (SimpleAddress) AddressSer.read(addressIn);
			ccAfterSer = (SimpleCreditCard) CreditCardSer.read(ccIn);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(accountAfterSer.getEmail());
        System.out.println(addressAfterSer.getCity());
        System.out.println(ccAfterSer.getAccountNumber());
        
    }
}
