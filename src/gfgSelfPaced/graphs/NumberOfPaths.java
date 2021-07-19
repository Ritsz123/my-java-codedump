package gfgSelfPaced.graphs;

import java.util.*;
import java.lang.*;
import java.io.*;
public class NumberOfPaths {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0) {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
            for(int i = 0; i < V; i++)
                adj.add(i, new ArrayList<Integer>());
            for(int i = 0; i < E; i++){
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                // adj.get(v).add(u);
            }
            String[] S1 = br.readLine().trim().split(" ");
            int source = Integer.parseInt(S1[0]);
            int destination = Integer.parseInt(S1[1]);
            Solution obj = new Solution();
            System.out.println(obj.countPaths(V, adj,source,destination));
        }
    }
}

class Solution {
    //Function to count paths between two vertices in a directed graph.
    public int countPaths(int V, ArrayList<ArrayList<Integer>> adj, int source, int destination) {
        // Code here
        ArrayList<Integer> path = new ArrayList<>();
        return dfs(adj, source, destination, path);
    }

    int dfs(ArrayList<ArrayList<Integer>> adj, int curr, int dest, ArrayList<Integer> path){
        path.add(curr);
        if (curr == dest){
            return 1;
        }
        int count = 0;
        for (int x: adj.get(curr)){
            count += dfs(adj, x, dest, path);
        }

        path.remove(path.size() -1);
        return count;
    }
}

//! input
/*

1
5 7
0 1
0 2
0 4
1 3
1 4
2 4
3 2
0 4

 */