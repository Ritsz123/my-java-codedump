package striversGraphSeries;


// ! undirected graph
// * input format
/*
    n m  number of nodes & edges
    m lines will follow each line having
    U V

    storing graph
    - Adjacency matrix
        if the input is 1 based index create int matrix of n+1 * n+1 for 0 based
        create n * n matrix filled with zeros
        for each m line of input go to matrix[U][V] & matrix[V][U] (in case of undirected graph)
            and mark it by 1
    - Adjacency list
        use an Arraylist<Arraylist<Integer>>
        add in case of 1 based indexing add n+1 number of new arraylists in it for 0 based add n new arraylists
        for every input of m go to arraylist[U] & add V, arraylist[V] & add U
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    private final ArrayList<ArrayList<Integer>> adj;

    Graph(ArrayList<ArrayList<Integer>> adj) {
        this.adj = adj;
    }

    public ArrayList<Integer> BFS(int start, boolean[] visited) {
        ArrayList<Integer> ans = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()){
            Integer node = q.poll();
            ans.add(node);
            for (Integer it: adj.get(node)){
                if(!visited[it]){
                    q.add(it);
                    visited[it] = true;
                }
            }
        }
        return ans;
    }

    private void DFS(int node,boolean[] visited,ArrayList<ArrayList<Integer>> adj,ArrayList<Integer> ans) {
        ans.add(node);
        visited[node] = true;
        for (Integer it: adj.get(node)) {
            if(!visited[it]){
                DFS(it,visited,adj,ans);
            }
        }
    }

    public ArrayList<Integer> DFS(int start,boolean[] visited) {
        ArrayList<Integer> ans = new ArrayList<>();
        DFS(start,visited,adj,ans);
        return ans;
    }

}

public class GraphRepresentation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        //! input
        // n m
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0;i<=n;i++){
            adj.add(new ArrayList<>());
        }

        // m lines
        for(int i = 0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }

        // * start BFS
        Graph g = new Graph(adj);
        boolean[] visitedBFs = new boolean[n+1];
        System.out.println("Using BFS...");
        for(int i = 1;i<=n;i++) {
            if(!visitedBFs[i]){
                ArrayList<Integer> al = g.BFS(i,visitedBFs);

                for (Integer it: al){
                    System.out.print(it + " ");
                }
            }
        }

        // * Start DFS
        System.out.println("\nUsing DFS...");
        boolean[] visited = new boolean[n+1];
        for (int i = 1;i<=n;i++){
            if (!visited[i]){
                ArrayList<Integer> al = g.DFS(i,visited);

                for (Integer it: al){
                    System.out.print(it + " ");
                }
            }
        }
    }
}
