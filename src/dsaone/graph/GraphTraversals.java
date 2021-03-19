package dsaone.graph;

import java.util.*;

public class GraphTraversals {
	
	private LinkedList<Integer> adj[];
	
//	constructor
	public GraphTraversals(int v) {
		adj = new LinkedList[v];
		for(int i=0;i<v;i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int source,int dest) {
		adj[source].add(dest);
		adj[dest].add(source);
	}
	
	public int bfs(int source,int dest) {
		boolean[] visited = new boolean[adj.length];
		int [] parent = new int[adj.length];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(source);
		visited[source] = true;
		parent[source]=-1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == dest) break;
			
			
			for(int neighbour:adj[cur]) {
				if(!visited[neighbour]) {
					q.add(neighbour);
					visited[neighbour]=true;
					parent[neighbour] = cur;
				}
			}
		}
		int cur = dest;
		int dist=0;
		while(parent[cur] !=-1) {
			System.out.print(cur + " => ");
			cur = parent[cur];
			dist++;
		}
		System.out.print(source);
		System.out.println();
		return dist;
	}
	
	public boolean dfs(int source,int destination,boolean[] visited) {
		if(source == destination) return true;
		
		for(int neighbour:adj[source]) {
			if(!visited[neighbour]) {
				visited[neighbour] = true;
				boolean isConnected = dfs(neighbour,destination,visited);
				if(isConnected) return true;
			}
		}
		return false;
	}
	
	public boolean dfsStack(int source,int dest) {
		boolean[] visited = new boolean[adj.length];
		visited[source] = true;
		
		Stack<Integer> stack = new Stack<>();
		
		stack.push(source);
		
		while(!stack.isEmpty()) {
			int curr = stack.pop();
			
			if(curr==dest) return true;
			
			for(int neighbour:adj[source]) {
				if(!visited[neighbour]) {
					visited[neighbour] = true;
					stack.push(neighbour);
				}
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of vertices & edges");
		int v = sc.nextInt();
		int e = sc.nextInt();
		
		GraphTraversals graph = new GraphTraversals(v);
		System.out.println("Enter " + e + " edges");
		for(int i=0;i<e;i++) {
			int source = sc.nextInt();
			int dest = sc.nextInt();
			
			graph.addEdge(source, dest);
		}
		
//		System.out.println("**********  BFS TRAVERSAL **********");
//		System.out.println("Enter source & destination");
//		int s= sc.nextInt();
//		int d = sc.nextInt();
//		System.out.println("Shortest path is " + dsaone.graph.bfs(s, d));
		
		
//		System.out.println("******** DFS TRAVERSAL *********");
//		System.out.println("Enter Source & destination");
//		int s = sc.nextInt();
//		int d = sc.nextInt();
//		boolean[] visited = new boolean[v];
//		if(dsaone.graph.dfs(s,d,visited)) {
//			System.out.println("Path available");
//		}else {
//			System.out.println("Path not available");
//		}
		
		System.out.println("******** DFS TRAVERSAL using stack *********");
		System.out.println("Enter Source & destination");
		int s = sc.nextInt();
		int d = sc.nextInt();
		System.out.println("Path available? " + graph.dfsStack(s, d));
	}
}
