package algorithms;

public class BinarySearch 
{
	public static int rank(int key, int[] array)
	{
		int lo = 0;
		int hi = array.length-1;
		
		while(lo <= hi)
		{
			int middle = lo + (hi-lo)/2;
			
			if(key < array[middle])
			{
				hi = middle - 1;
			}
			else if(key > array[middle])
			{
				lo = middle + 1;
			}
			else
			{
				return middle;
			}
		}

		return -1;
	}
	
	//Another method of recursion.
	public static int BiSearch(int key, int[] a, int lo, int hi)
	{
		if(lo > hi)
		{
			return -1;
		}
		
		int mid = lo + (hi-lo)/2;
		if(key > a[mid])
		{
			return BiSearch(key, a, mid+1, hi);
		}
		else if(key < a[mid])
		{
			return BiSearch(key, a, lo, mid-1);
		}
		else
		{
			return mid;
		}
		
	}
	
	//Test the BinarySearch function.
	public static void main(String[] args) 
	{
		int[] arraysort = {1,2,3,4,5,6,7,8,9,10};
		int key = 9;
		System.out.println("The key is :"+ key+ " and the index is : " + rank(key, arraysort));
		System.out.println("The key is :"+ key+ " and the index is : " + BiSearch(key, arraysort, 0, arraysort.length-1));
		System.out.println(arraysort[0]);
	}
}
