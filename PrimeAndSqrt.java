package algorithms;

public class PrimeAndSqrt 
{
	public static boolean isPrime(int N)
	{
		if(N < 2)
		{
			return false;
		}
		
		for(int i = 2; i*i <= N; i++)
		{
			if(N % i == 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static double Sqrt(double N)
	{
		if(N < 0)
		{
			return Double.NaN;
		}
		
		double err = 1e-15;
		double temp = N;
		
		while(Math.abs(temp - N/temp) > err * temp)
		{
			temp = (N/temp + temp) / 2.0;
		}
		
		return temp;
	}
	
	public static void main(String[] args) 
	{
		int key = 8;
		double num = 5.0;
		System.out.println( key + " is Prime? --" + isPrime(key));
		System.out.println("Sqrt of " + num + " is " + Sqrt(num));
	}
}
