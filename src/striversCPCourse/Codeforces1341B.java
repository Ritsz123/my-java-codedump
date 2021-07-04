package striversCPCourse;

import java.util.Scanner;

public class Codeforces1341B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            long[] arr = new long[n];
            for (int i = 0;i<n;i++){
                arr[i] = sc.nextLong();
            }
            int currPeaks = 0;
            int maxPeaks = 0;
            int maxPeaksIndex = 0;
            for (int i = 1; i<=k-2;i++) {
                if (arr[i] > arr[i-1] && arr[i] > arr[i+1]){
                    currPeaks++;
                }
            }
            maxPeaks = Math.max(maxPeaks,currPeaks);

            int l = 1;
            int r = k-1;
            while (r < n-1) {
                // the last element will come to mid and can be a peak
                if (arr[r] > arr[r+1] && arr[r] > arr[r-1]) {
                    currPeaks++;
                }
                // the 1st element in subarray was peak
                if (arr[l] > arr[l+1] && arr[l] > arr[l-1]) {
                    currPeaks--;
                }
                if (currPeaks > maxPeaks){
                    maxPeaks = currPeaks;
                    maxPeaksIndex = l;
                }
                l++;
                r++;
            }

            // * make 1 based indexing
            maxPeaks++;
            maxPeaksIndex++;
            System.out.println(maxPeaks + " " + maxPeaksIndex);
        }
    }
}
