package dsaone.recursion.advanced_recursion;

import java.util.Scanner;

public class JosephusProblem {

    int find(int n,int k){
        if(n==1){
            return 0;
        }
        return (find(n-1,k) + k) % n;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value of n :");
        int n = sc.nextInt();

        JosephusProblem p = new JosephusProblem();

        System.out.println("Enter value of K");

        System.out.println(p.find(n, sc.nextInt()));

    }
}
