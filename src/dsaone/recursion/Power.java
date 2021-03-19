package dsaone.recursion;

public class Power {
	
	//O(n)
	static int pow(int a,int b) {
		 if(b==1) return a;
		 return a * pow(a,b-1);
	}
	
	//O(log n)
	static int fastPow(int a,int b) {
		if(b==0) return 1;
		if(b%2==0) {
			return fastPow(a*a,b/2);
		}
		return a* fastPow(a,b-1);
	}
	
	public static void main(String[] args) {
		
		System.out.println(fastPow(2,10));
	}
}
