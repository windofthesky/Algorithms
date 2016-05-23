package algorithms;

public class Stopwatcher 
{
	private final long m_start;
	
	public Stopwatcher()
	{
		this.m_start = System.currentTimeMillis();
	}
	
	/*
	 * Calculate the time with "seconds".
	 */
	public double elapsedTimeSeconds()
	{
		long now = System.currentTimeMillis();
		return ((now - this.m_start)/1000.0);
	}
	
	/*
	 * Calculate the time with "milliseconds".
	 */
	public long elapsedTimeMillis()
	{
		long now = System.currentTimeMillis();
		return (now - this.m_start);
	}
	

	public static void main(String[] args) 
	{
		Stopwatcher test = new Stopwatcher();
		for(int i=0; i<100000; i++)
		{
			for(int j=0; j<100000; j++)
			{
				//do nothing.
			}	
		}
		System.out.println("Used Time: " + test.elapsedTimeMillis());
		System.out.println("Used Time: " + test.elapsedTimeSeconds());
	}
}
