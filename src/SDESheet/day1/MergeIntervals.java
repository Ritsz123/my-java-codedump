package SDESheet.day1;

import java.util.ArrayList;
import java.util.Arrays;

class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
}

public class MergeIntervals {
    public static void main(String[] args) {

        int[] starts =  {1,2,8,15};
        int[] ends = {3,6,10,18};

        Interval[] arr = new Interval[starts.length];

        for (int i = 0;i<starts.length;i++){
            arr[i] = new Interval(starts[i], ends[i]);
        }

        ArrayList<Interval> ans = mergeIntervals(arr);

        for (Interval e: ans){
            System.out.print(e.start + " " + e.end + "\n");
        }
    }

    private static ArrayList<Interval> mergeIntervals(Interval[] arr) {

        ArrayList<Interval> ans = new ArrayList<>();

        int s = arr[0].start;
        int e = arr[0].end;

        for (Interval x: arr){
            if (x.start <= e){
                e = Math.max(e, x.end);
            }else{
                ans.add(new Interval(s, e));
                s = x.start;
                e = x.end;
            }
        }
        ans.add(new Interval(s,e));
        return ans;
    }
}
