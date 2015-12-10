package ie.gmit.sw;


import java.rmi.Naming;
import java.rmi.RMISecurityManager;


public class Client {
	
	public static void main(String args[]){
		

        // I download server's stubs ==> must set a SecurityManager 
        System.setSecurityManager(new RMISecurityManager());
        try 
        { 
           VigenereBreaker obj = (VigenereBreaker) Naming.lookup( "12.0.0.1/cypher-service");
           System.out.println(obj.decrypt("./stuff.txt", 5)); 
           
        } 
        catch (Exception e) 
        { 
           System.out.println("HelloClient exception: " + e.getMessage()); 
           e.printStackTrace(); 
        } 
	}
}
