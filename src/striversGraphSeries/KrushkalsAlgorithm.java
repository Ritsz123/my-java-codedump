package striversGraphSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KrushkalsAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("input...");
        int n = sc.nextInt();
        int e = sc.nextInt();

        ArrayList<Edge> adj = new ArrayList<>();

        for (int i = 0;i<e;i++){
            adj.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        krushkal(adj, n, e);
    }

    static void krushkal(ArrayList<Edge> adj, int n, int e){
        Collections.sort(adj);
        int[] parent = new int[n];
        int[] rank = new int[n];

        for (int i = 0;i<n;i++){
            parent[i] = i;
        }

        int mstCost = 0;
        ArrayList<Edge> mst = new ArrayList<>();
        for (Edge edge: adj){
            if(findParent(edge.u, parent) != findParent(edge.v, parent)){
                mstCost += edge.weight;
                mst.add(edge);
                union(edge.u, edge.v, parent, rank);
            }
        }

        System.out.println(mstCost);
    }

    static void union(int u, int v, int[] parent, int[] rank){
        u = findParent(u, parent);
        v = findParent(v, parent);
        if (rank[u] < rank[v]){
            parent[u] = v;
        }else if(rank[v] < rank[u]){
            parent[v] = u;
        }else{
            parent[v] = u;
            rank[u]++;
        }
    }

    static int findParent(int num, int[] parent){
        if(parent[num] == num) return num;

        return parent[num] = findParent(parent[num], parent);
    }
}

class Edge implements Comparable<Edge>{
    int u;
    int v;
    int weight;

    Edge(int u, int v, int weight){
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        if (weight < edge.weight){
            return -1;
        }
        return 1;
    }
}


//! undirected weighted graph
/*
5 6
0 1 2
0 3 6
1 3 8
1 2 3
1 4 5
2 4 7
 */