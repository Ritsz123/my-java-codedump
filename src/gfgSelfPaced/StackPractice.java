package gfgSelfPaced;


import java.util.Stack;

public class StackPractice {

    public static void main(String[] args) throws Exception {
//        Implement two stacks in an array
//        implementTwoStacksInASingleArray();

        int[] arr1 = {15,13,12,14,16,8,6,4,10,15};
        int[] arr2 = {20,30,10,5,15};
//        stockSpanProblem(arr);

        int[] arr = {1,2,3,4,5};

//        prevGreaterElementInAnArray(arr1);
//
//        nextGreaterElementInAnArray(arr1);
//
//        prevSmallerElementInArray(arr1);
//
//        nextSmallerElementInArray(arr1);

//        System.out.println(largestRectangularArea(arr));

//        System.out.println(largestRectangularAreaBetter(arr));

//        System.out.println(largestRectangularAreaEfficient(arr));

//        designAStackSupportingGetMinAndGetMaxOperation();
//        Stack<Integer> st = new Stack<>();
//        for (int i = 1;i<=6;i++){
//            st.push(i);
//        }
//        deleteMiddleElement(st,6,6);
//        while (!st.isEmpty()){
//            System.out.print(st.pop() + " ");
//        }

//        String infix = "a^b^c";
//        System.out.println(infix + " " + infixToPostfix(infix));

//        String postfix = "123+*8-";
//        System.out.println(evaluationOfPostfixExpression(postfix));

        int[][] mat = {
                { 0,0,1,1 },
                { 1,0,1,0 },
                { 0,0,0,0 },
                { 1,1,1,0 }
        };

        System.out.println(findCelebrity(mat));
    }

    static int findCelebrity(int [][] m) {
        Stack<Integer> st = new Stack<>();
        for (int i= 0;i<m.length;i++){
            st.push(i);
        }

        while (st.size() > 1){
            int x = st.pop();
            int y = st.pop();

            // x knows y ?
            if (m[x][y] == 1) {
                // yes
                //then x cannot be celeb
                st.push(y);
            } else {
                // x can be celeb
                st.push(x);
            }
        }
        int c = st.pop();
        for (int i = 0;i<m.length;i++){
            if (i != c && m[c][i] != 0 && m[i][c] != 1){
                return -1;
            }
        }
        return c;
    }

    static int evaluationOfPostfixExpression(String str) throws Exception {
        Stack<Integer> st = new Stack<>();
        for (int i = 0;i<str.length();i++){
            char ch = str.charAt(i);
            if (Character.isDigit(ch)){
                st.push(Character.getNumericValue(ch));
            }else{
                int num1 = st.pop(), num2 = st.pop();
                int ans;
                if (ch == '-'){
                    ans = num2 - num1;
                }else if (ch == '+'){
                    ans = num2 + num1;
                }else if (ch == '*'){
                    ans = num2 * num1;
                }else {
                    ans = num2 / num1;
                }
                st.push(ans);
            }
        }
        if (st.size() > 1){
            throw new Exception("Invalid state");
        }
        return st.pop();
    }

    static String infixToPostfix(String str) throws Exception {
        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0;i< str.length();i++){
            char ch = str.charAt(i);

            if (Character.isLetterOrDigit(ch)){
                ans.append(ch);
            }else if (ch == '('){
                st.push(ch);
            }else if (ch == ')'){
                while (st.peek() != '('){
                    ans.append(st.pop());
                }
                st.pop();
            } else {
                while (!st.isEmpty() && prec(ch) <= prec(st.peek())){
                    ans.append(st.pop());
                }
                st.push(ch);
            }
        }

        while (!st.isEmpty()){
            if (st.peek() =='('){
                throw new Exception("Invalid Infix Expression");
            }
            ans.append(st.pop());
        }

        return ans.toString();
    }

    static int prec (char ch){
        switch(ch){
            case '+' :
            case '-' : return 1;
            case '*' :
            case '/' : return 2;
            case '^' : return 3;
            default: return -1;
        }
    }

    static void deleteMiddleElement(Stack<Integer> st,int size,int curr){
        if (curr == 0){
            return;
        }
        int mid = (size+1)/2;
        int x = st.pop();
        deleteMiddleElement(st,size,curr-1);
        if (curr != mid){
            st.push(x);
        }
    }

    static void designAStackSupportingGetMinAndGetMaxOperation(){
        MinMaxStack mms = new MinMaxStack();
        mms.push(5);
        System.out.println(mms.getMax());
        mms.push(8);
        System.out.println(mms.getMin());
        mms.push(2);
        System.out.println(mms.getMin());
        mms.pop();
        System.out.println(mms.getMin());
        System.out.println(mms.getMax());
        mms.pop();
        System.out.println(mms.getMax());
    }

//    Efficient solution
    static int largestRectangularAreaEfficient(int [] arr){
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int ans = 0;
        for (int i=0;i<n;i++){
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]){
                int tp = st.pop();
                int curr = arr[tp] * (st.isEmpty()? i : (i - st.peek() - 1));
                ans = Math.max(curr,ans);
            }
            st.push(i);
        }
        while (!st.isEmpty()){
            int tp = st.pop();
            int curr = arr[tp] * (st.isEmpty()? n :(n - st.peek() - 1));
            ans = Math.max(curr,ans);
        }
        return ans;
    }

    static int largestRectangularAreaBetter(int[] arr){
//     !  find previous smaller element for every element of array
//     !  find next smaller element for every element

        Stack<Integer> st = new Stack<>();
        int[] prev = new int[arr.length];
        int[] next = new int[arr.length];

        for (int i=0;i<arr.length;i++){
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]){
                st.pop();
            }
            prev[i] = st.isEmpty() ? -1 : st.peek();

            st.push(i);
        }

        st.clear();

        for (int i = arr.length-1;i>=0;i--){
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]){
                st.pop();
            }
            next[i] = st.isEmpty() ? arr.length : st.peek();

            st.push(i);
        }

//        for (int i=0;i<arr.length;i++){
//            System.out.println(prev[i] + " " + next[i]);
//        }

        int[] width = new int[arr.length];
        for (int i = 0;i<arr.length;i++){
            width[i] = next[i] - prev[i] - 1;
        }

        int max = 0;
        for (int i=0;i<arr.length;i++){
            int curr = width[i] * arr[i];
            max = Math.max(max,curr);
        }
        return max;
    }

//    brute
    static int largestRectangularArea(int[] arr){
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i=0;i<n;i++){
           int curr = arr[i];
            for (int j= i-1;j>=0;j--){
               if (arr[i] <= arr[j]){
                    curr+=arr[i];
               }else break;
            }
            for (int j=i+1;j<n;j++){
                if (arr[i] <= arr[j]){
                    curr+=arr[i];
                }else break;
            }
            max = Math.max(max,curr);
        }
        return max;
    }

    static void nextSmallerElementInArray(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] next = new int[arr.length];

        for (int i=arr.length-1;i>=0;i--){
            while (!st.isEmpty() && st.peek() >= arr[i]){
                st.pop();
            }
            next[i] = st.isEmpty()? -1: st.peek();
            st.push(arr[i]);
        }

        for (int x:next){
            System.out.print(x + " ");
        }
        System.out.println();
    }

    static void prevSmallerElementInArray(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] prev = new int[arr.length];

        for (int i=0;i<arr.length;i++){
            while (!st.isEmpty() && st.peek() >= arr[i]){
                st.pop();
            }
            prev[i] = st.isEmpty() ? -1 : st.peek();

            st.push(arr[i]);
        }
        for (int x:prev){
            System.out.print(x + " ");
        }
        System.out.println();
    }

    static void nextGreaterElementInAnArray(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];

        for (int i=arr.length-1;i>=0;i--){
            while (!st.isEmpty() && st.peek() <= arr[i]){
                st.pop();
            }
            ans[i] = st.isEmpty()? -1 : st.peek();

            st.push(arr[i]);
        }

        for (int a:ans){
            System.out.print(a + " ");
        }
        System.out.println();
    }

    static void prevGreaterElementInAnArray(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];

        for (int i=0;i<arr.length;i++){
            while (!st.isEmpty() && st.peek() <= arr[i]){
                st.pop();
            }
            ans[i] = st.isEmpty()? -1 : st.peek();
            st.push(arr[i]);
        }
        for (int x:ans){
            System.out.print(x + " ");
        }
        System.out.println();
    }

    static void stockSpanProblem(int [] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];

        st.push(0);
        ans[0]=1;
        for (int i=1;i<arr.length;i++){
            while (!st.isEmpty() && arr[st.peek()]<=arr[i]){
                st.pop();
            }
            ans[i] = st.isEmpty()? i+1 : (i-st.peek());
            st.push(i);
        }
        for (int x: ans){
            System.out.print(x + " ");
        }
        System.out.println();
    }

    static void implementTwoStacksInASingleArray(){
        TwoStacks stacks = new TwoStacks(6);
        stacks.pop1();
        stacks.pop2();
        stacks.push1(1);
        stacks.push2(2);
        stacks.push2(3);
        stacks.push1(4);
        stacks.push1(5);
        stacks.push2(6);
        stacks.push2(7);
        stacks.push1(8);
        System.out.println("Size 1: "+ stacks.size1());
        System.out.println("size 2: "+ stacks.size2());
        System.out.println("Pop 2: " + stacks.pop2());
        System.out.println("Pop 1: " + stacks.pop1());
    }
}

class MinMaxStack{
    private Stack<Integer> ms = new Stack<>();
    private Stack<Integer> as = new Stack<>();
    private Stack<Integer> mx = new Stack<>();
    public void push(int num){
        ms.push(num);
        if (as.isEmpty() || ms.peek() <= as.peek()){
            as.push(num);
        }
        if (mx.isEmpty() || ms.peek() >= mx.peek()){
            mx.push(num);
        }
    }

    public int pop(){
        if (ms.peek().equals(as.peek())){
            as.pop();
        }
        if (ms.peek().equals(mx.peek())){
            mx.pop();
        }
        return ms.pop();
    }

    public int getMin(){
        return as.peek();
    }

    public int getMax(){
        return mx.peek();
    }
}

class TwoStacks{
    private int n;
    private int top1;
    private int top2;
    private int[] arr;

    TwoStacks(int n){
        this.n=n;
        arr = new int[n];
        top1=-1;
        top2=n;
    }

    void push1(int number){
        if (top1 < top2-1){
            arr[++top1] = number;
        }else{
            System.out.println("Stack 1 Overflow");
        }
    }

    void push2(int number){
        if (top1 < top2-1){
            arr[--top2] = number;
        }else{
            System.out.println("Stack 2 Overflow");
        }
    }

    int pop1(){
        if (top1> -1){
            return arr[top1--];
        }else{
            System.out.println("Stack underFlow");
            return -1;
        }
    }

    int pop2(){
        if (top2<n){
            return arr[top2++];
        }else{
            System.out.println("Stack 2 underflow");
            return -1;
        }
    }

    int size1(){
        return top1+1;
    }

    int size2(){
        return n-top2;
    }
}
