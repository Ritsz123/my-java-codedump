package gfgSelfPaced;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueuePractice {
    public static void main(String[] args) {

//        int[] arr = { 4, 6, 7, 4 };
//        int[] brr = { 6, 5, 3, 5 };
////        System.out.println(circularTour(arr,brr));
//
//        Queue<Integer> q = createQueue(arr);
//        reverseQueue(q);
//        printQ(q);


        int[] arr = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
        int[] brr = { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
        maximumOfAllSubArraysOfSizeKBrute(brr,4);
        System.out.println();
        maximumOfAllSubArraysOfSizeKEfficient(brr,4);
    }

    static void maximumOfAllSubArraysOfSizeKEfficient(int[] arr, int k){
        Deque<Integer> dq = new LinkedList<>();
        int n = arr.length;

        for (int i = 0;i<k;i++) {
            //! new element is greater then remove all small
            while (!dq.isEmpty() && arr[dq.getLast()] <= arr[i]){
                dq.removeLast();
            }
            dq.addLast(i);
        }
        System.out.print(arr[dq.getFirst()] + " ");

        for (int i = k;i<n;i++){
            // * check if the element is out of window
            while (!dq.isEmpty() && dq.getFirst() <= i-k){
                dq.removeFirst();
            }

            //! new element is greater remove all small
            while (!dq.isEmpty() && arr[dq.getLast()] <= arr[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
            System.out.print(arr[dq.getFirst()] + " ");
        }
    }


    // *brute
    static void maximumOfAllSubArraysOfSizeKBrute(int[] arr,int k) {
        int n = arr.length;
        for (int i = 0;i <= n-k;i++){
            int max = arr[i];
            for (int j = i;j<i+k;j++){
                if (arr[j] > max){
                    max = arr[j];
                }
            }
            System.out.print(max + " ");
        }
    }

    static void reverseQueue(Queue<Integer> q) {
        if(q.isEmpty()){
            return;
        }

        int num = q.poll();
        reverseQueue(q);
        q.add(num);
    }

    static int circularTour(int[] petrol, int[] distance) {
        int n = petrol.length;
        int cp = 0;
        for (int i = 0;i<n;i++){
            cp+= petrol[i] - distance[i];
        }
        if (cp <0) return -1;

        int p = 0;
        int start = 0;
        for (int i = 0;i<n;i++){
            p += petrol[i] - distance[i];
            if (p<0){
                p = 0;
                start = i+1;
            }
        }
        return start;
    }

    static Queue<Integer> createQueue(int[] arr){
        Queue<Integer> q = new LinkedList<>();
        for (int x: arr){
            q.add(x);
        }
        return q;
    }

    static void printQ(Queue<?> q){
        while (!q.isEmpty()){
            System.out.print(q.poll() + " ");
        }
    }
}
