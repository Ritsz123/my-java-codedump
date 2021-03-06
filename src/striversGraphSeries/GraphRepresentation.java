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

import java.util.*;

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

    public boolean detectCycleInUndirectedGraphBFS(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited){
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

    public boolean detectCycleInUndirectedGraphDFS(int start, int parent, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[start] = true;
        for (Integer it: adj.get(start)){
            if (visited[it]){
                if (it != parent) return true;
            }else{
                return detectCycleInUndirectedGraphDFS(it,start,visited,adj);
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
                    colors[x] = 1 - colors[curr];
                }else{
                    if (colors[x] == colors[curr]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkBipartiteDFS(int curr, ArrayList<ArrayList<Integer>> adj, int[] colors){
        if (colors[curr] == -1){
            colors[curr] = 0;
        }
        for (int x: adj.get(curr)){
            if (colors[x] == -1){
                colors[x] = 1 - colors[curr];
                if(!checkBipartiteDFS(x,adj,colors)) return false;
            }else if (colors[x] == colors[curr]){
                return false;
            }
        }
        return true;
    }

    public boolean detectCycleInDirectedGraphDFS(int curr, ArrayList<ArrayList<Integer>> adj, int[] visited, int[] dfsVisited){
        visited[curr] = 1;
        dfsVisited[curr] = 1;
        for (int x: adj.get(curr)){
            if (visited[x] == 0){
                if(detectCycleInDirectedGraphDFS(x, adj, visited, dfsVisited)){
                    return true;
                }
            }else if (dfsVisited[x] == 1){
                return true;
            }
        }
        dfsVisited[curr] = 0;
        return false;
    }

    public boolean detectCycleInDirectedGraphBFS(ArrayList<ArrayList<Integer>> adj, int n) {
        int[] indegree = new int[n+1];
        for (int i=1;i<=n;i++){
            for (int x : adj.get(i)){
                indegree[x]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1;i<=n;i++){
            if (indegree[i] == 0){
                q.add(i);
            }
        }
        int count = 0;
        while (!q.isEmpty()){
            int curr = q.poll();
            count++;
            for (int x:adj.get(curr)){
                indegree[x]--;
                if (indegree[x] == 0){
                    q.add(x);
                }
            }
        }
        if (count == n) return false;
        return true;
    }

    public void topologicalSortDFS(int curr, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> st){
        visited[curr] = true;
        for (int x : adj.get(curr)){
            if (!visited[x]){
                topologicalSortDFS(x, adj, visited, st);
            }
        }
        st.push(curr);
    }

    public ArrayList<Integer> topologicalSortBFS(ArrayList<ArrayList<Integer>> adj, int n){
        int[] inDegree = new int[n+1];
        for (int i = 1;i<=n;i++){
            for (int x: adj.get(i)){
                inDegree[x]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i=1;i<=n;i++){
            if (inDegree[i] == 0){
                q.add(i);
            }
        }
        ArrayList<Integer> topoSort = new ArrayList<>();
        while (!q.isEmpty()) {
            int curr = q.poll();
            topoSort.add(curr);
            for (int x: adj.get(curr)){
                inDegree[x]--;
                if (inDegree[x] == 0){
                    q.add(x);
                }
            }
        }
        return topoSort;
    }

    public void shortestPathInUndirectedGraph(ArrayList<ArrayList<Integer>> adj, int n, int src) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        dist[src] = 0;
        while (!q.isEmpty()){
            int curr = q.remove();
            for (int x: adj.get(curr)){
                if ((dist[curr] + 1) < dist[x]){
                    dist[x] = dist[curr] + 1;
                    q.add(x);
                }
            }
        }

        for(int x: dist){
            System.out.print(x != Integer.MAX_VALUE ? x + " " : -1 + " ");
        }
    }
}

public class GraphRepresentation {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        //! input
        // n m
        System.out.println("Choose Graph type : \n1: Undirected Graph \n2: Directed Graph");
        int graphType = sc.nextInt();
        System.out.println("Enter number of nodes & vertices");
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
            if(graphType == 1) {
                //! Undirected graph
                adj.get(v).add(u);
            }
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

        //! check if graph is Bipartite bfs
//        checkBipartite(n, adj,Traverse.BFS, g);

        //! check if graph is Bipartite dfs
//        checkBipartite(n, adj, Traverse.DFS, g);

        //! detect cycle in directed graph DFS
//        detectCycleInDirectedGraph(n, adj, g, graphType);

        // detect cycle in directed graph BFS
//        System.out.println("is cycle directed BFS " + g.detectCycleInDirectedGraphBFS(adj,n));

        //! topological sort order DFS
//        topologicalSortOrderOfGraph(n, adj, Traverse.DFS, g, graphType);

        //!topological sort order bfs
        //also known as kahn's algorithm
//        topologicalSortOrderBFS(n,adj,g);

        //! shortest path from a node in graph
        System.out.println("shortest path");
        g.shortestPathInUndirectedGraph(adj, n, 1);

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
                    isCycle = g.detectCycleInUndirectedGraphBFS(i,adj,visited);
                } else if (t == Traverse.DFS){
                    isCycle = g.detectCycleInUndirectedGraphDFS(i,-1,visited,adj);
                }
                if (isCycle) break;
            }
        }

        System.out.println("\nisCycle using " + t + " : " + isCycle);
    }

    static void checkBipartite(int n, ArrayList<ArrayList<Integer>> adj, Traverse t, Graph g) {
        int[] color = new int[n+1];
        for (int i = 1;i<=n;i++){
            color[i] = -1;
        }
        boolean isBipartite = true;
        for (int i = 1;i<=n;i++){
            if (color[i] == -1){
                if(!g.checkBipartiteDFS(i,adj,color)) {
                    isBipartite = false;
                    break;
                }
            }
        }
        System.out.println("\nisBipartite using " + t + " : " + isBipartite);
    }

    static void detectCycleInDirectedGraph(int n, ArrayList<ArrayList<Integer>> adj, Graph g, int graphType) throws Exception {
        if (graphType == 1){
            throw new Exception("This method only works on directed graph");
        }

        int[] visited = new int[n+1];
        int[] dfsVisited = new int[n+1];
        boolean isCycle = false;

        for (int i = 1;i<=n;i++){
            if (visited[i] == 0){
                isCycle = g.detectCycleInDirectedGraphDFS(i,adj,visited,dfsVisited);
                if (isCycle){
                    break;
                }
            }
        }

        System.out.println("Is cycle in directed graph : " + isCycle);
    }

    static void topologicalSortOrderOfGraph(int n, ArrayList<ArrayList<Integer>> adj, Traverse t, Graph g, int graphType) throws Exception {
        if (graphType != 2) {
            throw new Exception("Only allowed on Directed Graph..");
        }

        boolean[] visited = new boolean[n+1];
        Stack<Integer> st = new Stack<>();

        for (int i = 1;i<=n;i++) {
            if(!visited[i]){
                g.topologicalSortDFS(i, adj, visited, st);
            }
        }

        System.out.println("Topological order using " + t);

        while (!st.isEmpty()){
            System.out.print(st.pop() + " ");
        }
    }

    static void topologicalSortOrderBFS(int n, ArrayList<ArrayList<Integer>> adj, Graph g){
        ArrayList<Integer> toposeq = g.topologicalSortBFS(adj, n);
        System.out.println("Topo sort using BFS: ");
        for (int x: toposeq) {
            System.out.print(x + " ");
        }
    }
}

//input

// * undirected
//1
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

// * directed
//2
//5 6
//1 2
//2 3
//3 4
//4 5
//5 1
//1 3

// * for toposort
//2
//6 6
//6 3
//6 1
//5 1
//5 2
//3 4
//4 2