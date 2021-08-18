package SDESheet.day3;

//! calculate m power n

public class FastPower {
    static int mod = (int)1e9 + 7;
    public static void main(String[] args) {
        int m = 2;
        int n = 10;

        System.out.println(findPower(m,n));
    }

    static long findPower(int m, int n) {
        if(n <= 0) return 1;

        long res = 1;
        if(n % 2 == 0){
            res = (findPower(m, n/2) * findPower(m, n/2)) % mod;
        } else {
            res = (m * (findPower(m, n-1))) % mod;
        }
        return res;
    }
}
