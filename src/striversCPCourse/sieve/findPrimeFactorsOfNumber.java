package striversCPCourse.sieve;

public class findPrimeFactorsOfNumber {
    int max = (int) 1e6;
    int[] sieve = new int[max];

    void sieve() {
        for(int i = 2; i<=max;i++){
            sieve[i] = i;
        }
        for (int i = 2; i <= max;i++){
//            check if sieve[i] is prime
            if (sieve[i] == i){
                for (int j = i * i; j<= max;j += i){
                    if (sieve[j] == j){
                        //mark it with minimum prime factor
                        sieve[j] = i;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 100;

        for (int i = 2; i * i <=n;i++){
            while (n % i == 0){
                System.out.println(i);
                n = n/i;
            }
        }
        if(n >= 2){
            System.out.println(n);
        }
    }
}
