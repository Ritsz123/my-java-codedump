package striversGraphSeries;

import java.util.*;

class Pair {
    int v;
    int wt;
    Pair(int v, int wt){
        this.v = v;
        this.wt = wt;
    }
}

public class DirectedWeightedGraph {

    static void topoSort(int start, ArrayList<ArrayList<Pair>> adj, Stack<Integer> st, boolean[] visited){
        visited[start] = true;
        for (Pair x: adj.get(start)){
            if (!visited[x.v]){
                topoSort(x.v, adj, st, visited );
            }
        }
        st.push(start);
    }

    static void shortestPathInDirectedWeightedGraph(ArrayList<ArrayList<Pair>> adj, int source, int n) {
        // perform topo sort with dfs
        // do BFS
        Stack<Integer> st = new Stack<>();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean[] visited = new boolean[n];
        for (int i = 0;i<n;i++){
            if (!visited[i]){
                topoSort(i,adj, st, visited);
            }
        }

        // at this point we have topo sort in stack
        dist[source] = 0;
        while (!st.isEmpty()) {
            int curr = st.pop();
            if (dist[curr] != Integer.MAX_VALUE){
                for (Pair x: adj.get(curr)){
                    int currentDistance = dist[curr] + x.wt; // the main part...
                    if (currentDistance < dist[x.v]) {
                        dist[x.v] = currentDistance;
                    }
                }
            }

        }

        for(int x: dist){
            if (x != Integer.MAX_VALUE){
                System.out.print(x + " ");
            }else{
                System.out.print("-1 ");
            }
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
        }

        shortestPathInDirectedWeightedGraph(adj, 1, n);
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