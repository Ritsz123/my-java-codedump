package SDESheet.day2;

import java.util.*;

// the idea is : first and last num of each row = 1
// for every [i][j] ans is [i-1][j] + [i-1][j-1]

public class PascalsTriangle {
    public static void main(String[] args) {

        int height = 15;
        List<List<Integer>> triangle = pascalsTriangle(height);

        for (List<Integer> e: triangle){
            for (int x: e){
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> pascalsTriangle(int height) {
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0;i<height;i++) {
            List<Integer> level = new ArrayList<>();
            for (int j = 0;j <= i;j++) {
                if (j == 0 || j == i){
                    level.add(1);
                } else {
                    List<Integer> prev = ans.get(ans.size() - 1);
                    level.add(prev.get(j - 1) + prev.get(j));
                }
            }
            ans.add(new ArrayList<>(level));
        }
        return ans;
    }
}
