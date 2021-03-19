package contests;

import java.io.*;
import java.util.Arrays;

public class CFB {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t--!=0) {
			int n = Integer.parseInt(br.readLine());

			long[] x = new long[n];
			long[] y = new long[n];

			for (int i = 0; i < n; i++) {
				String[] line = br.readLine().split(" ");
				x[i] = Long.parseLong(line[0]);
				y[i] = Long.parseLong(line[1]);
			}
			long ans;
			if(n%2==0){
				Arrays.sort(x);
				Arrays.sort(y);
				long rex = x[n/2] - x[n/2-1] + 1;
				long rey = y[n/2] - y[n/2-1] + 1;
				ans = rex*rey;
			}else{
				ans=1;
			}
			System.out.println(ans);
		}
	}
}
