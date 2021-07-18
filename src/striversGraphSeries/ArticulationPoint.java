package striversGraphSeries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ArticulationPoint {

    static void findArticulationPoint(ArrayList<ArrayList<Integer>> adj, int n){
        boolean[] visited = new boolean[n];
        int[] dis = new int[n];
        int[] low = new int[n];

        HashSet<Integer> ans = new HashSet<>();

        int timer = 0;
        for (int i = 0;i<n;i++){
            if (!visited[i]) {
                dfs(adj, i, -1, dis, low, visited, timer, ans);
            }
        }

        System.out.println("Articulation points are...");
        for (int x: ans){
            System.out.println(x);
        }
    }

    static void dfs(ArrayList<ArrayList<Integer>> adj, int curr, int parent, int[] dis, int[] low, boolean[] visited, int timer, HashSet<Integer> ans){
        visited[curr] = true;
        dis[curr] = low[curr] = timer++;

        int child = 0;
        for (int x: adj.get(curr)){
            if (x == parent) continue;

            if (!visited[x]){
                dfs(adj, x, curr, dis, low, visited, timer,ans);
                low[curr] = Math.min(low[curr], low[x]);

                if (low[x] >= dis[curr] && parent != -1){
                    ans.add(curr);
                }
                child++;
            }else{
                low[curr] = Math.min(low[curr], low[x]);
            }
        }

        if (parent == -1 && child > 1) ans.add(curr);

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

        findArticulationPoint(adj, n);
    }
}
