package striversGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BellmanFordAlgorithm {

    static class Node {
        int u,v,weight;
        Node(int u, int v, int weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static void bellmanFordAlgo(ArrayList<Node> adj, int n){
        //! step1: fill distance[] as infinity and mark dis[source] = 0;
        int[] dis = new int[n];
        Arrays.fill(dis,10000000);
        dis[0] = 0;

        //! Step2: for n-1 times relax the edges
        for(int i = 1;i<n;i++){
            for(Node curr: adj){
                if(dis[curr.u] + curr.weight < dis[curr.v]) {
                    dis[curr.v] = dis[curr.u] + curr.weight;
                }
            }
        }

        //! step3: store the distance[] to temp array and again relax the edges for 1 time
        // if the array changes then there is a negative cycle
        // else the array is answer..

        int[] arr = new int[n];
        for (int i = 0;i<n;i++){
            arr[i] = dis[i];
        }

        for (Node curr: adj){
            if (dis[curr.u] + curr.weight < dis[curr.v]){
                dis[curr.v] = dis[curr.u] + curr.weight;
            }
        }

        boolean cycle = false;
        for (int i = 0;i<n;i++){
            if(arr[i] != dis[i]) {
                cycle = true;
                System.out.println("Negative cycle at " + i);
                break;
            }
        }

        if (!cycle){
            for (int x: dis){
                System.out.print(x + " ");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Input...");
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<Node> adj = new ArrayList<>();

        for (int i = 0;i<e;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            adj.add(new Node(a,b,c));
        }

        bellmanFordAlgo(adj,n);
    }
}

//! input
/*
6 7
3 2 6
5 3 1
0 1 5
1 5 -3
1 2 -2
3 4 -2
2 4 3

 */
