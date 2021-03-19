package a2ojladder;

import java.io.*;

class Solution{
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        int rq = Integer.parseInt(line[0]);
        int cq = Integer.parseInt(line[1]);
        boolean[][] arr = new boolean[n][n];
        for (int i=0;i<k;i++){
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            arr[x-1][y-1] = true;
        }
        System.out.println(check(arr,rq-1,cq-1));
    }
    static int check(boolean[][] arr,int x,int y){
        int count=0;
        // up
        int i=x,j=y;
        while (i>=0 && !arr[i][j]){
            i--;
            count++;
        }
        // down
        i = x;
        while (i<arr.length && !arr[i][j]){
            i++;
            count++;
        }
        // left
        i=x;
        while (j>=0 && !arr[i][j]){
            j--;
            count++;
        }

        //right
        j = y;
        while (j<arr[0].length && !arr[i][j]){
            j++;
            count++;
        }

        // left up
        j = y;
        while (i>=0 && j>=0 && !arr[i][j]){
            i--;
            j--;
            count++;
        }

        // right down
        i = x; j = y;
        while (i<arr.length && j<arr.length && !arr[i][j]){
            i++;
            j++;
            count++;
        }

        //left down
        i = x; j = y;
        while (j>=0 && i<arr.length && !arr[i][j]){
            j--;
            i++;
            count++;
        }

        //right up
        i = x; j = y;
        while (j<arr.length && i>=0 && !arr[i][j]){
            j++;
            i--;
            count++;
        }
        return count;
    }
}