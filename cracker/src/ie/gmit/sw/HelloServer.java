package ie.gmit.sw;

import java.rmi.*;
import java.rmi.server.*;

class HelloServer{
	public static void main(String args[]){
		try
		{
			System.out.println("The HelloWorld server � is ready...");
		}
		catch(Exception e)
		{
			System.out.println("Error creating � CrackerHandler object.");
		}
	}
} 
