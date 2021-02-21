package edu.uw.rgm.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;

/**
 * Class for serializing instances of classes that implement the Account interface.
 * @author Odinahon Saydahmedova
 *
 */
public final class AccountSer {
	private static final String NULL_STR ="<null>";
	private AccountSer() {
		
	}
	/**
	 * Reads an Account object from an input stream.
	 * @param in - the input stream to read from
	 * @return the Account object read from stream
	 * @throws edu.uw.ext.framework.account.AccountException - if an error occurs reading from stream or instantiating the object.
	 */
	public final static Account read(InputStream in) throws edu.uw.ext.framework.account.AccountException{
		final DataInputStream din = new DataInputStream(in);
		try(ClassPathXmlApplicationContext appContext = 
			new ClassPathXmlApplicationContext ("context.xml")){
			final Account acct = appContext.getBean(Account.class);
			
			acct.setName(din.readUTF());
			acct.setPasswordHash(readByteArray(din));
			acct.setBalance(din.readInt());
			acct.setFullName(readString(din));
			acct.setPhone(readString(din));
			acct.setEmail(readString(din));
			return acct;
		}catch(final BeansException ex) {
			throw new AccountException("Unable to create account instance", ex);
		}catch(final IOException ex) {
			throw new AccountException("Unable to read persisted account date.",ex);
		}
		
	}
	/**
	 * Writes an Account object to an output stream.
	 * @param out - the output stream to write to
	 * @param acct - the Account object to write
	 * @throws edu.uw.ext.framework.account.AccountException- if an error occurs writing to stream
	 */
	public final static void write(OutputStream out, Account acct) 
			throws edu.uw.ext.framework.account.AccountException{
		try {
			final DataOutputStream dos = new DataOutputStream(out);
			dos.writeUTF(acct.getName());
			writeByteArray(dos, acct.getPasswordHash());
			dos.writeInt(acct.getBalance());
			writeString(dos, acct.getFullName());
			writeString(dos, acct.getPhone());
			writeString(dos, acct.getEmail());
			dos.flush();
		}catch(final IOException ex) {
			throw new AccountException("Failed to write account data.", ex);
		}
	}
	private static void writeString( final DataOutputStream out, final String s) 
			throws IOException {
		out.writeUTF(s == null ? NULL_STR : s);
	}
	
	private static String readString(final DataInputStream in) throws IOException{
		final String s = in.readUTF();
		return NULL_STR.equals(s)?null:s;
	}
	
	private static void writeByteArray(final DataOutputStream out, final byte[] b) throws IOException{
		final int len =(b == null )? -1:b.length;
		out.writeInt(len);
		
		if(len > 0) {
			out.write(b);
		}
	}
	private static byte[] readByteArray(final DataInputStream in) throws IOException {
		byte [] bytes =null;
		final int len = in.readInt();
		
		if(len >=0) {
			bytes = new byte[len];
			in.readFully(bytes);
		}
		return bytes;
	}
}












