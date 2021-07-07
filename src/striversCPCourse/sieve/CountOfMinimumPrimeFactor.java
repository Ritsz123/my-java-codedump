package striversCPCourse.sieve;

public class CountOfMinimumPrimeFactor {
    static int MAX = (int) 1e6;
    static int[] sieve = new int[MAX + 2];
    static int[] freq = new int[MAX + 2];

    static void createSieve() {

        for (int i = 2;i <= MAX;i++){
            sieve[i] = 1;
        }

        for (int i = 2;i * i <= MAX;i++){
            if (sieve[i] == 1){
                freq[i]++;
                for (int j = i * i;j <= MAX; j += i){
                    sieve[j] = 0;
                    freq[i]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        createSieve();

        System.out.println(freq[97]);
//        System.out.println(freq[2]);
    }
}
