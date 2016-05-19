package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Counter 
{
	private String m_name;
	
	private static long count;
	
	private String getCounterName()
	{
		return this.m_name;
	}
	
	private long getCount()
	{
		return Counter.count;
	}
	
	public void increase()
	{
		if(Counter.count <= Long.MAX_VALUE)
		{
			Counter.count+=1;
		}
		else
		{
			Counter.count = 0;
		}
	}
	
	public Counter(String name)
	{
		if(name != null)
		{
			this.m_name = name;
		}
		
		Counter.count = 0;
	}

	public static void main(String[] args) throws InterruptedException 
	{
		
		int threadNum = 20;
		for (int i = 0; i < threadNum; i++) //open threadNum threads.
		{
            Thread t = new WorkerThread();
            t.start();
        }
        while (true)//wait all the threads end 
		{
            if (!WorkerThread.hasThreadRunning()) 
			{
                break;
            }
            Thread.sleep(500);
        }
	}
	
	public static class WorkerThread extends Thread 
	{
        private static List<Thread> runningThreads = new ArrayList<Thread>();
        private static Counter test = new Counter("test");
        public WorkerThread() {}
 
        @Override
        public void run() 
		{
        	
        	for(int j = 0; j<10; j++)
        	{
        		test.increase();
        		System.out.println(test.getCounterName());
        		System.out.println(test.getCount());
        	}
        }
 
        public void regist(Thread t) 
		{
            synchronized (runningThreads) 
			{
                runningThreads.add(t);
            }
        }
 
        public void unRegist(Thread t) 
		{
            synchronized (runningThreads) 
			{
                runningThreads.remove(t);
            }
        }
 
        public static boolean hasThreadRunning() 
		{            
            return (runningThreads.size() > 0);  //check runningThreads is empty or not.
        }
    }
}
