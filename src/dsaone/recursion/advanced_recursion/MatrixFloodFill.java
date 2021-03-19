package dsaone.recursion.advanced_recursion;

public class MatrixFloodFill {

	static void printMatrix(int a[][]) {
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int array[][] = {
				{1,12,1,1,1,2,2},
				{1,1,2,3,1,1,5},
				{1,12,2,3,4,5,4},
				{1,1,2,3,4,5,4},
				{1,1,2,3,1,1,5},
						};
		printMatrix(array);
		System.out.println("\n");
		floodFill(array,0,0,90,1);
		printMatrix(array);
	}
	static void floodFill(int arr[][],int r,int c,int toFill,int prevFill) {
		int rows = arr.length;
		int cols = arr[0].length;
		if( r<0 || r>=rows || c<0 || c>=cols ) {
			return;
		}
		if(arr[r][c]!=prevFill) {
			return;
		}
		arr[r][c]=toFill;
		floodFill(arr,r-1,c,toFill,prevFill);
		floodFill(arr,r,c-1,toFill,prevFill);
		floodFill(arr,r+1,c,toFill,prevFill);
		floodFill(arr,r,c+1,toFill,prevFill);
		
	}

}
