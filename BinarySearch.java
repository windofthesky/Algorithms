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
	
	//Test the BinarySearch function.
	public static void main(String[] args) 
	{
		int[] arraysort = {1,2,3,4,5,6,7,8,9,10};
		int key = 9;
		System.out.println("The key is :"+ key+ " and the index is : " + rank(key, arraysort));
	}
}
