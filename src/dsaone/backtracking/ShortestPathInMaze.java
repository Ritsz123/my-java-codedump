package dsaone.backtracking;

public class ShortestPathInMaze {

	public static void main(String[] args) {
		int maze[][] =
			{
					{ 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
					{ 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
					{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
					{ 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
					{ 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
					{ 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
					{ 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
					{ 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
					{ 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
					{ 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
			};
		
		
		int path = shortestPath(maze, 0, 0, 9, 9);
		if(path>=100000) System.out.println("Path not found");
		else System.out.println("Shortest path is: "+path);

	}
	static int shortestPath(int[][] arr, int sx,int sy, int dx, int dy) {
		int rows = arr.length;
		int cols = arr[0].length;
		
		boolean visited[][] = new boolean[rows][cols];
		
		return shortestPath(arr, sx, sy, dx, dy, visited);
		
	}
	
	static boolean isValid(int[][] arr,int sx,int sy,boolean[][] visited) {
		int rows = arr.length;
		int cols = arr[0].length;
		//check if the point is out of maze or is already visited or is a wall(not allowed to visit)
		if (sx >= 0 && sy >= 0 && sx < rows && sy < cols && arr[sx][sy] == 1 &&
				!visited[sx][sy]) {
			return true;
		}
		return false;
	
		
	}
	
	static int shortestPath(int[][] arr,int sx,int sy,int dx,int dy,boolean[][] visited) {
		if(!isValid(arr, sx, sy, visited)) return 10000000; // some number greater than m*n (size of maze)
		if(sx==dx && sy==dy) return 0;
		
		visited[sx][sy] = true;
		
		int left = shortestPath(arr, sx, sy-1, dx, dy, visited) + 1;
		int bottom = shortestPath(arr, sx+1, sy, dx, dy, visited)+1;
		int right = shortestPath(arr, sx, sy+1, dx, dy, visited)+1;
		int top = shortestPath(arr, sx-1, sy, dx, dy, visited)+1;
		
		//most important line to make dsaone.backtracking work
		visited[sx][sy] = false;
		
		return Math.min(Math.min(left, bottom), Math.min(right, top));
	}
}
