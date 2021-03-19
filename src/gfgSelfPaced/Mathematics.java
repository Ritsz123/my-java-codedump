package gfgSelfPaced;

import java.util.Arrays;

public class Mathematics {
    public static void main(String[] args) {
        int num = 4;

//        System.out.println("is prime: " + checkPrime(num));
//        System.out.println("is prime efficient: " + mostEfficientCheckPrime(num));

//        System.out.println("prime factorials:");
//        findPrimeFactors(num);

//        int num2 = 6;
//        System.out.println("gcd: " + gcd(num,num2));
//        System.out.println("LCM: " + lcm(num,num2));

//        printAllDivisorsOfNumber(15);
//        printAllDivisorsInAscendingOrder(15);

//        printAllPrimeNumbersTillN(15);
//        sieveAlgoToPrintPrime(15);

//        System.out.println("Power " + power(2,7));
//        System.out.println("Iterative Power " + iterativePower(2,7));

    }

    static int iterativePower(int x,int y){
        int ans = 1;
        while (y>0){
            if (y % 2==1){
                ans = ans * x;
            }
            x = x * x;
            y = y /2;
        }
        return ans;
    }

    static int power(int x,int y){
        if(y==0) return 1;
        int temp = power(x,y/2);
        temp = temp * temp;
        if(y%2==0){
            return temp;
        }else{
            return temp * x;
        }
    }

    static void sieveAlgoToPrintPrime(int n){
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime,true);
        for (int i=2;i*i <= n;i++){
            if (isPrime[i]){
                for (int j=i*i;j<=n;j+=i){
                    isPrime[j] = false;
                }
            }
        }
        for (int i=2;i<=n;i++){
            if (isPrime[i]){
                System.out.print(i + " ");
            }
        }
    }

    //brute
    static void printAllPrimeNumbersTillN(int n){
        for (int i=2;i<=n;i++){
            if (checkPrime(i)){
                System.out.print(i + " ");
            }
        }
    }

    static void printAllDivisorsInAscendingOrder(int num){
        int i=1;
        for (i=1;i*i<=num;i++){
            if (num%i==0){
                System.out.print(i + " ");
            }
        }
        for (;i>=1;i--){
            if(num%i==0){
                System.out.print(num/i + " ");
            }
        }
    }

    static void printAllDivisorsOfNumber(int num){
        for (int i=1;i*i<=num;i++){
            if(num%i==0){
                System.out.print(i + " " );
                if(num / i != i){
                    System.out.print(num/i + " ");
                }
            }
        }
    }

    static void findPrimeFactors(int num){
        if(num<=1) return;

        for (int i=2;i*i<=num;i++){
            while(num%i==0){
                System.out.print(i + " ");
                num=num/i;
            }
        }
        if(num>1){
            System.out.println(num);
        }
    }

    static boolean mostEfficientCheckPrime(int num){
        if(num<=1) return false;
        if(num%2==0 || num%3==0) return false;
        for (int i=5;i*i<=num;i+=6){
            if(num % i ==0 || num % (i+2)==0){
                return false;
            }
        }
        return true;
    }

    static boolean checkPrime(int num){
        if(num<=1) return false;
        for (int i=2;i*i<=num;i++){
            if(num%i==0) return false;
        }
        return true;
    }

    static int lcm(int a,int b){
        return (a*b)/gcd(a,b);
    }

    static int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a % b);
    }
}
