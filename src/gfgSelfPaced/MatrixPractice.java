package gfgSelfPaced;

public class MatrixPractice {

	public static void main(String[] args) {
		
//		int [][] arr = {{ 1,2,3,4 },
//						{ 5,6,7,8 },
//						{ 9,10,11,12 },
//						{ 13,14,15,16 }};
//		
//		int [][] brr = {{ 1,2,3,4 },
//						{ 5,6,7,8 },
//						{ 9,10,11,12 }};
		
//		printMatrixInSnakePattern(arr);
//		printBoundryOfTheMatrix(arr);
//		printBoundryOfMatrixInClockwisePattern(arr);
//		transposeOfMatrix(arr);
//		rotateTheMatrix90DegreeAntiClockWise(arr);
//		printMatrixInSpiralOrder(arr);
		
		int mat[][] = {{ 10,20,30,40 },
						{ 15,25,35,45 },
						{ 27,29,37,48 },
						{ 32,33,39,50 }};
		searchElementInRowWiseAndColumWiseSortedMatrix(mat, 29);
	}
	
	
	static void searchElementInRowWiseAndColumWiseSortedMatrix(int [][] arr,int num) {
		int n = arr.length;
		boolean flag = false;
		int j=n-1,i=0;
		while(j>=0 && i<n && !flag) {
			int x = arr[i][j];
			if(x==num) {
				flag = true;
				System.out.println("Found at " + i + " " + j);
			} 
			if(x > num) {
				j--;
			}else {
				i++;
			}
		}
		
		if(!flag) {
			System.out.println("Not found");
		}
	}
	
	static void printMatrixInSpiralOrder(int [][] arr) {
		int rows = arr.length,cols=arr[0].length;
		int left=0,right = cols-1,top=0,bottom=rows-1;
		while(left<=right && top<=bottom) {
			for(int i=left;i<=right;i++) {
				System.out.print(arr[top][i] +" ");
			}
			top++;
			for(int i=top;i<=bottom;i++) {
				System.out.print(arr[i][right] + " ");
			}
			right--;
//			these 2 if conditions to check if the matrix contains only single row or column left to print
//			if we remove these conditions we will see the repeat printing of the element with single row col.
			if(top<=bottom) {
				for(int i=right;i>=left;i--) {
//					to print left to right in reverse order
					System.out.print(arr[bottom][i] + " ");
				}
				bottom--;
			}
			if(left<=right) {
				for(int i=bottom;i>=top;i--) {
//					to print top to bottom in reverse order
					System.out.print(arr[i][left] + " ");
				}
				left++;
			}
			
		}
	}
	
	
	static void rotateTheMatrix90DegreeAntiClockWise(int [][] arr) {
		int n = arr.length;
		
//		Naive approach
//		
//		int [][] mat = new int [n][n];
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				mat[n-j-1][i]=arr[i][j];
//			}
//		}
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				arr[i][j]=mat[i][j];
//			}
//		}
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		
//		Optimised ..
		
//		
		transposeOfMatrix(arr);
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<n;j++) {
				int temp = arr[i][j];
				arr[i][j]=arr[n-1-i][j];
				arr[n-1-i][j] = temp;
			}
		}
		System.out.println("Rotation 90 degree :");
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void transposeOfMatrix(int [][]arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<i;j++) {
				int t = arr[i][j];
				arr[i][j]=arr[j][i];
				arr[j][i]=t;
			}
		}
		System.out.println("Transpose: ");
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void printBoundryOfMatrixInClockwisePattern(int [][] arr) {
		if(arr.length==1) {
			for(int i=0;i<arr[0].length;i++) {
				System.out.println(arr[0][i] + " ");
			}
		}else if(arr[0].length==1) {
			for(int i=0;i<arr.length;i++) {
				System.out.print(arr[i][0] + " ");
			}
		}else {
			for(int i=0;i<arr[0].length;i++) {
				System.out.print(arr[0][i]+" ");
			}
		
			for(int i=1;i<arr.length;i++) {
				System.out.print(arr[i][arr[i].length-1]+ " ");
			}
			for(int i=arr[0].length-2;i>=0;i--) {
				System.out.print(arr[arr.length-1][i] + " ");
			}
			for(int i=arr.length-2;i>0;i--) {
				System.out.print(arr[i][0] + " ");
			}
		}
	}
	
	static void printBoundryOfTheMatrix(int [][] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				if(i==0 || i==arr.length-1) {
					System.out.print(arr[i][j] + " ");
				}else if(j==0 || j==arr[i].length-1) {
					System.out.print(arr[i][j] + " ");
				}else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
	
	
	static void printMatrixInSnakePattern(int [][]arr) {
		
		for(int i=0;i<arr.length;i++) {
			if(i%2==0) {
				for(int j=0;j<arr[i].length;j++) {
					System.out.print(arr[i][j] + " ");
				}
			}else {
				for(int j=arr[i].length-1;j>=0;j--) {
					System.out.print(arr[i][j] + " ");
				}
			}
		}
	}
}
