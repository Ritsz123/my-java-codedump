package striversGraphSeries;

import java.util.ArrayList;
import java.util.Scanner;

public class PrimsAlgorithm {

    static class Pair {
        int v;
        int weight;
        Pair(int v, int wt){
            this.v = v;
            this.weight = wt;
        }
    }

    static void primsAlgorithm(ArrayList<ArrayList<Pair>> adj, int n){
        int[] weights = new int[n];
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        for (int i = 0;i<n;i++){
            weights[i] = Integer.MAX_VALUE;
        }

        weights[0] = 0;

        //as we will only have n-1 edges in mst therefore only traverse till n-1
        for(int i = 0;i<n-1;i++){
            int min = Integer.MAX_VALUE, u = 0;

            //pick vertex with minimum weight such that is not visited
            //greedy strategy....
            for (int v = 0; v<n;v++) {
                if (visited[v] == false && weights[v] < min){
                    min = weights[v];
                    u = v;
                }
            }

            //mark the selected weight as used
            visited[u] = true;

            // for all adjacent nodes if current weight is less than selected weight then selected weight = current weight
            for (Pair x: adj.get(i)) {
                if (visited[x.v] == false && x.weight < weights[x.v]){
                    parent[x.v] = u;
                    weights[x.v] = x.weight;
                }
            }
        }

        for (int i = 0;i<n;i++){
            System.out.println(parent[i] + " -> " + i);
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

        primsAlgorithm(adj, n);
    }
}

// ! input
/*
    5 6
    0 2 1
    0 1 3
    1 3 2
    2 1 9
    2 3 5
    3 4 6
*/