package dsaone.acedsa;

import java.util.*;

class Graph{
    public int v;
    private LinkedList<Integer>  adj[]; // adj list


//    Constructor
    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];

        for(int i=0;i<v;i++){
            adj[i] = new LinkedList<>();
        }
    }


    void addEdge(int v,int w,boolean bidir){
        adj[v].add(w);
        if(bidir){
            adj[w].add(v);
        }
    }

    private void DFSHelper(int v,boolean[] visited){
        visited[v] = true;
        System.out.print(v + " ");

//        for all adjacent vertices
        Iterator<Integer> i = adj[v].listIterator();
        while(i.hasNext()){
            int n = i.next();
            if(!visited[n]){

//                recursive call
                DFSHelper(n,visited);
            }

        }
    }

    public void DFS(int startingPoint){

//      call the actual function
        DFSHelper(startingPoint,new boolean[v]);
    }

    public void BFS(int startingPoint){
        boolean[] visited = new boolean[v];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[startingPoint] = true;

        queue.add(startingPoint);

        while (!queue.isEmpty()){
            startingPoint = queue.poll();
            System.out.print(startingPoint + " ");

            Iterator<Integer> i = adj[startingPoint].listIterator();

            while (i.hasNext()){
                int n = i.next();
                if(!visited[n]){

                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}

public class GraphAlgos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices");

        int n = sc.nextInt();
        Graph g = new Graph(n);
        System.out.println("Enter number edges");
        int e = sc.nextInt();
        while (e--!=0){
            System.out.println("Enter to & from edge");

//            as of now the dsaone.graph is bidirectional || undirected
            g.addEdge(sc.nextInt(),sc.nextInt(),false);
        }
//        DFS
        System.out.println("------------  DFS  ------------");
        g.DFS(2);

        System.out.println();

        System.out.println("------------  BFS  ------------");
        g.BFS(2);

    }

}
