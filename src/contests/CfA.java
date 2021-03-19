package contests;

import java.io.*;

public class CfA {

	public static void main(String[] args)throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		while (t--!=0){
			int n = Integer.parseInt(br.readLine());
			long [] arr = new long[n];
			String[] line = br.readLine().split(" ");
			boolean poss = false;
			for(int i=0;i<n;i++){
				arr[i] = Long.parseLong(line[i]);
			}

			long sum = 0;
			for (int i=0;i<n;i++){
				sum+=arr[i];
				long need = (i * (i+1))/2;

				if(sum<need){
					poss = true;
					break;
				}
			}
			System.out.println(poss?"NO":"YES");
		}
	}
}
