package gfgSelfPaced;

import java.util.LinkedList;
import java.util.Queue;

public class QueuePractice {
    public static void main(String[] args) {

        int[] arr = { 4, 6, 7, 4 };
        int[] brr = { 6, 5, 3, 5 };
//        System.out.println(circularTour(arr,brr));

        Queue<Integer> q = createQueue(arr);
        reverseQueue(q);
        printQ(q);

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
