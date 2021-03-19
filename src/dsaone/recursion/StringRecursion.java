package dsaone.recursion;

public class StringRecursion {

    private boolean isPalindrome(String str,int l,int r){
        if(l>=r){
            return true;
        }
        if(str.charAt(l)!=str.charAt(r)) return false;

        return isPalindrome(str,l+1,r-1);
    }

    boolean isPalindrome(String str){
        return isPalindrome(str,0,str.length()-1);
    }

    void powerSet(String str,int i,String cur){
        if(i==str.length()){
            System.out.println(cur);
            return;
        }
        powerSet(str,i+1,cur);
        powerSet(str,i+1,cur+str.charAt(i));
    }

    void permute(char[] str,int l,int r){
        if (l==r){
            System.out.println(new String(str));
            return;
        }
        for (int i=l;i<=r;i++){
            swap(str,l,i);
            permute(str,l+1,r);
            swap(str,l,i);
        }
    }

    private void swap(char [] arr,int l,int r){
        char t = arr[l];
        arr[l] = arr[r];
        arr[r] = t;
    }
}

class Runner{
    public static void main(String[] args) {
        StringRecursion stringRecursion = new StringRecursion();

//        System.out.println(stringRecursion.isPalindrome("abba"));
//        stringRecursion.powerSet("abc",0,"");
        stringRecursion.permute("abc".toCharArray(),0,2);
    }
}
