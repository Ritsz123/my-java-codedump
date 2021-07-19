package striversGraphSeries;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class KosarajuAlgorithm {

    static void kosarajuAlgo(ArrayList<ArrayList<Integer>> adj, int n){
        //step1: find topo sequence
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[n];

        for(int i = 0;i<n;i++){
            if(!visited[i]) topoSequence(adj, i, st, visited);
        }

        //step2: transpose (reverse links)
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
        for (int i = 0;i<n;i++){
            transpose.add(new ArrayList<>());
        }

        for (int i = 0;i<n;i++){
            visited[i] = false;
            for (int x: adj.get(i)){
                transpose.get(x).add(i);
            }
        }

        //step3: perform dfs based on toposequence
        System.out.println("\nStrongly connected components:");
        while (!st.isEmpty()){
            int curr = st.pop();
            if (!visited[curr]) {
                dfsRev(transpose, curr, visited);
                System.out.println();
            }
        }

    }

    static void topoSequence(ArrayList<ArrayList<Integer>> adj, int curr, Stack<Integer> st, boolean[] visited){
        visited[curr] = true;
        for(int x: adj.get(curr)) {
            if(!visited[x])
            topoSequence(adj, x, st, visited);
        }
        st.push(curr);
    }

    static void dfsRev(ArrayList<ArrayList<Integer>> adj, int curr, boolean[] visited){
        visited[curr] = true;
        System.out.print(curr + " ");
        for (int x: adj.get(curr)) {
            if(!visited[x]) dfsRev(adj,x, visited);
        }
    }

    public static void main(String[] args) {
        System.out.println("Input....");
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0;i<e;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }

        kosarajuAlgo(adj, n);
    }
}


//! input
/*

5 5
0 1
1 2
2 0
1 3
3 4

 */