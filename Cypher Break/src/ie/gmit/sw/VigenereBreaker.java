package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.*;

public interface VigenereBreaker  extends Remote {

	//String sayHello() throws RemoteException;
	public String decrypt(String cypherText, int maxKeyLength) throws RemoteException;
	
}
