package dsaone.recursion.advanced_recursion;

public class StringPermutations {

	public static void main(String[] args) {
		String s = "ritesh";
		permutations(s,0,s.length()-1);
	}
	static void permutations(String str,int l,int r) {
		if(l==r) {
			System.out.println(str);
			return;
		}
		for(int i=l;i<=r;i++) {
			str=interchange(str, l, i);
			permutations(str, l+1, r);
			str=interchange(str, l, i);
		}
		
	}
	static String interchange(String str,int l,int r) {
		char[] arr = str.toCharArray();
		char temp = arr[l];
		arr[l]=arr[r];
		arr[r]=temp;
		return new String(arr);
	}
}
