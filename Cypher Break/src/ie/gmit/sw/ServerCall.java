package ie.gmit.sw;

import java.rmi.*;
import java.rmi.server.*;

class ServerCall{
	
	public class Request {
		private String cypherText;
		private int maxKeySize;
		private long jobNumber;
		
		public Request(String cText, int maxNo, long jobNo)
		{
			setCypherText(cText);
			setMaxKeySize(maxNo);
			setJobNumber(jobNo);
		}
		
		public long getJobNumber() {
			return jobNumber;
		}
		public void setJobNumber(long jobNumber) {
			this.jobNumber = jobNumber;
		}
		public int getMaxKeySize() {
			return maxKeySize;
		}
		public void setMaxKeySize(int maxKeySize) {
			this.maxKeySize = maxKeySize;
		}
		public String getCypherText() {
			return cypherText;
		}
		public void setCypherText(String cypherText) {
			this.cypherText = cypherText;
		}
		
	}
} 
