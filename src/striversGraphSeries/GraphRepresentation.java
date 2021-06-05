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

enum Traverse {
    DFS,
    BFS
}

class Node {
    int curr;
    int parent;
    Node(int curr,int parent){
        this.curr = curr;
        this.parent = parent;
    }
}

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

    public boolean detectCycleBFS(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited){
        Queue<Node> q = new LinkedList<>();

        visited[start] = true;
        q.add(new Node(start,-1)); //as initial node does not have parent

        while (!q.isEmpty()){
            Node current = q.poll();
            for (Integer it: adj.get(current.curr)) {
                if (!visited[it]){
                    q.add(new Node(it, current.curr));
                    visited[it] = true;
                } else {
                    if (it != current.parent){
                        // ! Cycle is found....
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean detectCycleDFS(int start, int parent,boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[start] = true;
        for (Integer it: adj.get(start)){
            if (visited[it]){
                if (it != parent) return true;
            }else{
                return detectCycleDFS(it,start,visited,adj);
            }
        }
        return false;
    }

    public boolean checkBipartiteBFS(int start, ArrayList<ArrayList<Integer>> adj, int[] colors) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        colors[start] = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int x: adj.get(curr)){
                if (colors[x] == -1){
                    q.add(x);
                }else{
                    if (colors[x] == colors[curr]){
                        return false;
                    }
                }
            }
        }
        return true;
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
            // !as it is undirected graph
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        Graph g = new Graph(adj);

        // * start BFS
//        traverseGraph(n, Traverse.BFS, g);
        // * Start DFS
//        traverseGraph(n, Traverse.DFS, g);

        //! detect cycle in graph using bfs
//        detectCycle(n,adj,Traverse.BFS, g);

        //! detect cycle DFS
//        detectCycle(n,adj,Traverse.DFS,g);

        checkBipartite(n,adj,g);
    }

    static void traverseGraph(int n, Traverse t, Graph g) {
        System.out.println("\nUsing " + t + " traversal...");

        boolean[] visited = new boolean[n+1];
        for(int i = 1;i<=n;i++) {
            if(!visited[i]){
                ArrayList<Integer> al;
                if (t == Traverse.BFS){
                     al = g.BFS(i,visited);
                }else{
                     al = g.DFS(i,visited);
                }

                for (Integer it: al){
                    System.out.print(it + " ");
                }
            }
        }
    }

    static void detectCycle(int n, ArrayList<ArrayList<Integer>> adj, Traverse t, Graph g) {
        boolean isCycle = false;
        boolean[] visited = new boolean[n+1];
        for (int i = 1;i<=n;i++){
            if (!visited[i]) {
                if (t == Traverse.BFS){
                    isCycle = g.detectCycleBFS(i,adj,visited);
                } else if (t == Traverse.DFS){
                    isCycle = g.detectCycleDFS(i,-1,visited,adj);
                }
                if (isCycle) break;
            }
        }

        System.out.println("\nisCycle using " + t + " : " + isCycle);
    }

    static void checkBipartite(int n, ArrayList<ArrayList<Integer>> adj, Graph g) {
        int[] color = new int[n+1];
        boolean isBipartite = true;
        for (int i = 1;i<=n;i++){
            if (color[i] == -1){
                isBipartite =  isBipartite && g.checkBipartiteBFS(i,adj,color);
            }
        }
        System.out.println("\nisBipartite :" + isBipartite);
    }
}

//input

//11 10
//1 2
//2 4
//3 5
//5 8
//5 6
//6 7
//8 9
//9 10
//10 7
//7 10
//7 11