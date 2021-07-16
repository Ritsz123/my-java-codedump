package striversGraphSeries;

import java.util.ArrayList;
import java.util.Scanner;

public class FindBridges {

    static void findBridges(int n, ArrayList<ArrayList<Integer>> adj){
        int[] low = new int[n];
        int[] disc = new int[n];
        boolean[] visited = new boolean[n];

        int timer = 0;
        for (int i = 0;i<n;i++, timer++){
            if(!visited[i]) {
                dfs(i, adj, -1, disc, low, visited, timer);
            }
        }
    }

    static void dfs(int curr, ArrayList<ArrayList<Integer>> adj, int parent, int[] dis, int[] low, boolean[] visited, int timer){
        visited[curr] = true;
        dis[curr] = low[curr] = timer++;
        for(int x: adj.get(curr)){
            if(x == parent) continue;

            if(!visited[x]) {
                dfs(x, adj, curr, dis, low, visited, timer);
                low[curr] = Math.min(low[curr], low[x]);
                if(low[x] > dis[curr]){
                    System.out.println(x + " -> " + curr);
                }
            }else {
                low[curr] = Math.min(low[curr], low[x]);
            }
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
            adj.get(v).add(u);
        }

        findBridges(n, adj);
    }
}
