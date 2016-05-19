/*
 * None Rights Reserve. Welcome to use.
 * >_< 
 */

package algorithms;

import java.io.IOException;

public class BinarySearch 
{
	/*
	 * Binary search with the 'int' type input value.
	 */
	public static int BiSearch(int key, int[] array)
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
	
	/*
	 * Binary search with the 'long' type input value.
	 */
	public static long BiSearch(long key, long[] array)
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
	
	/*
	 * Binary search with the 'float' type input value.
	 */
	public static int BiSearch(float key, float[] array)
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
	
	/*
	 * Binary search with the 'double' type input value.
	 */
	public static int BiSearch(double key, double[] array)
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
	
	/*
	 * Binary search with the 'int' type input value--recursion version.
	 */
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
	
	/*
	 * Binary search with the 'long' type input value--recursion version.
	 */
	public static int BiSearch(long key, long[] a, int lo, int hi)
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
	
	/*
	 * Binary search with the 'float' type input value--recursion version.
	 */
	public static int BiSearch(float key, float[] a, int lo, int hi)
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
	
	/*
	 * Binary search with the 'double' type input value--recursion version.
	 */
	public static int BiSearch(double key, double[] a, int lo, int hi)
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
	public static void main(String[] args) throws IOException 
	{
		int[] arraysort = {1,2,3,4,5,6,7,8,9,10};
		long[] arraysort_long = {1,2,3,4,5,6,7,8,9,10};
		float[] arraysort_float = {1.0f,2.0f,3.0f,4.0f,5.0f,6.0f,7.0f,8.0f,9.0f,10.0f};
		double[] arraysort_double = {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0};
		int key = 9;
		long key_long = 9;
		float key_float = 9.0f;
		double key_double = 9.0;
		
		System.out.println("The key is :"+ key+ " and the index is : " + BiSearch(key, arraysort));
		System.out.println("The key is :"+ key+ " and the index is : " + BiSearch(key_long, arraysort_long));
		System.out.println("The key is :"+ key+ " and the index is : " + BiSearch(key_float, arraysort_float));
		System.out.println("The key is :"+ key+ " and the index is : " + BiSearch(key_double, arraysort_double));
		System.out.println("The key is :"+ key+ " and the index is : " + BiSearch(key, arraysort, 0, arraysort.length-1));
		System.out.println("The key is :"+ key+ " and the index is : " + BiSearch(key_long, arraysort_long, 0, arraysort_long.length-1));
		System.out.println("The key is :"+ key+ " and the index is : " + BiSearch(key_float, arraysort_float, 0, arraysort_float.length-1));
		System.out.println("The key is :"+ key+ " and the index is : " + BiSearch(key_double, arraysort_double, 0, arraysort_double.length-1));
	}
}
