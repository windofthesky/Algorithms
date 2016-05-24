package algorithms;

public class MaxMQ<Key extends Comparable<Key>> 
{
	private Key[] pq;
	private int N = 0;
	
	private boolean less(int i, int j)
	{
		return pq[i].compareTo(pq[j]) > 0; 
	}

	private void exchange(int i, int j)
	{
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp; 
	}
	
	/*
	 * From down to up.
	 */
	private void swim(int k)
	{
		while(k > 1 &&  less(k/2, k))
		{
			exchange(k/2, k);
			k = k/2;
		}
	}
	
	/*
	 * From up to down.
	 */
	private void sink(int k)
	{
		while(2*k < N)
		{
			int j = 2*k;
			if(j<N && less(j, j+1))
			{
				j++;
			}
			
			if(!less(k, j))
			{
				break;
			}
			
			exchange(k, j);
			k = j;
		}
	}
	
	public static void main(String[] args) 
	{

	}

}
