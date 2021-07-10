package striversGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraAlgorithm {

    static class Pair implements Comparable<Pair> {
        int value;
        int weight;
        Pair(int v, int wt){
            this.value = v;
            this.weight = wt;
        }

        @Override
        public int compareTo(Pair pair) {
            if (this.weight < pair.weight) return -1;
            return 1;
        }
    }

    static void dijkstra(ArrayList<ArrayList<Pair>> adj, int n, int source){
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[source] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(source, 0));
        while (!pq.isEmpty()){
            Pair curr = pq.remove();

            for (Pair x: adj.get(curr.value)){
                if (distance[curr.value] + x.weight < distance[x.value]) {
                    distance[x.value] = distance[curr.value] + x.weight;
                    pq.add(new Pair(x.value, distance[x.value]));
                }
            }
        }

        for (int x: distance){
            System.out.print(x + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println("Input....");
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i = 0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for (int i = 0;i<e;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            adj.get(a).add(new Pair(b, c));
            adj.get(b).add(new Pair(a, c));
        }

        dijkstra(adj, n, 0);
    }
}
