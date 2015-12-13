package ie.gmit.sw;


import java.util.*;
import java.util.concurrent.*;

public class VigenereRequestManager
{
	private static final int maxCapacity = 10;
	private BlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(maxCapacity);
	private Map<Long, String> out = new ConcurrentHashMap<Long, String>();
	private String cypherText;
	VigenereHandler handler;
	
	public VigenereRequestManager(Request r)
	{
		add(r);
	}
	public void add(final Request r)
	{
		try
		{
			
			
			//queue.put(r)//blocks if queue full
			Thread t1 = new Thread(new Runnable()
			{
				public void run()
				{
					
						try
						{
							queue.put(r);
							//writes to map with encoded text so it can be passed to VignereHandler
							out.put(r.getJobNumber(),r.getCypherText());				
							handler = new VigenereHandler(queue, out);
							//rewrites map with decoded text
							out.put(r.getJobNumber(), handler.returnResult());
						//	System.out.println(r.getJobNumber() + " " + r.getCypherText() + " " + handler.returnResult());
						}
						catch(Exception e)
						{
							System.out.println(e);
						}
					
				}
			});
			
		t1.start();
		//Needs to wait to give thread chance to run.
		t1.join();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public String getResult(long jobNumber) throws InterruptedException
	{
		Thread t2 = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					String result =	out.get(jobNumber);
					cypherText = result;
					System.out.println(cypherText);
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		});
		t2.start();
		t2.join();
		out.remove(jobNumber);
		handler.removeRequest(jobNumber);
		return cypherText;
	}
	public static void main(String[] args) throws InterruptedException 
	{
		Request req = new Request("Testing", 5, 1);
		VigenereRequestManager vrm = new VigenereRequestManager(req);
		vrm.getResult(1);
	}
}