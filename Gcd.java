package algorithms;

public class Gcd
{
    public static int gcd(int p, int q)
    {
        if(q == 0)
            return p;
        
        int r = p % q;
    
        return gcd(q, r);
    }
    
    public static void main(String[] args) 
    {
    	int test = gcd(15, 6);
    	System.out.println("The answer is: " + test);
    }
}
