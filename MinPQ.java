package algorithms;

import java.util.Comparator;

public class MinPQ<Key extends Comparable<Key>> 
{
	private Key[] pq;
	private int N = 0;
	private Comparator<Key> comparator;
	
	private boolean less(int i, int j)
	{
		if (comparator == null) 
		{
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else 
        {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
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
		while(k > 1 &&  !less(k/2, k))
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
			if(j<N && !less(j, j+1))
			{
				j++;
			}
		
			if(less(k, j))
			{
				break;
			}
		
			exchange(k, j);
			k = j;
		}
	}
	
	public MinPQ(int maxN)
	{
		pq = (Key[]) new Comparable[maxN + 1];
	}
	
	public boolean isEmpty()
	{
		return N == 0;
	}
	
	public int size()
	{
		return N;
	}
	
	public void insert(Key v)
	{
		pq[++N] = v;
		swim(N);
	}
	
	public Key delMin()
	{
		Key max = pq[1];
		exchange(1, N--);
		pq[N+1] = null;
		sink(1);
		
		return max;
	}
	
	//-----Test Code----------------------
	public static void main(String[] args) 
	{
		//Test the String type.
		MinPQ<String> pq = new MinPQ<String>(10);
        pq.insert("eee");
        pq.insert("fff");
        pq.insert("ggg");
        pq.insert("aaa");
        pq.insert("bbb");
        pq.insert("ccc");
        pq.insert("ddd");
        System.out.println("(" + pq.size() + " on pq)");
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println("(" + pq.size() + " left on pq)");
        
        //Test the Integer type.
        MinPQ<Integer> pq_int = new MinPQ<Integer>(10);
        pq_int.insert(7);
        pq_int.insert(6);
        pq_int.insert(5);
        pq_int.insert(4);
        pq_int.insert(3);
        pq_int.insert(2);
        pq_int.insert(1);
        System.out.println("(" + pq_int.size() + " on pq)");
        System.out.println(pq_int.delMin());
        System.out.println(pq_int.delMin());
        System.out.println(pq_int.delMin());
        System.out.println("(" + pq_int.size() + " left on pq)");
	}
}
