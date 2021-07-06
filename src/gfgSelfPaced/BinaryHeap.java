package gfgSelfPaced;

import java.util.Collections;
import java.util.PriorityQueue;

public class BinaryHeap {
    private final int[] arr;
    private final int capacity;
    private int size = 0;

    BinaryHeap(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
    }

    int getLeft(int i){
        return arr[getLeftIndex(i)];
    }

    int getLeftIndex(int i){
        return i * 2 + 1;
    }

    int getRight(int i){
        return arr[getRightIndex(i)];
    }

    int getRightIndex(int i){
        return i * 2 + 2;
    }

    int getParent(int i){
        return arr[parentIndex(i)];
    }

    int parentIndex(int i){
        return (i-1)/2;
    }

    void insertMinHeap(int num) {
        if(size == capacity) return;

        size++;
        arr[size-1] = num;

        for (int i = size-1; i != 0 && arr[parentIndex(i)] > arr[i]; ){
            //swap arr[i] & arr[parent[i]]
            int t = arr[parentIndex(i)];
            arr[parentIndex(i)] = arr[i];
            arr[i] = t;

            i = parentIndex(i);
        }
    }

    void minHeapify(int i){
        int left = getLeftIndex(i);
        int right = getRightIndex(i);
        int smallest = i;
        if (left < size && arr[left] < arr[smallest]){
            smallest = left;
        }
        if (right < size && arr[right] < arr[smallest]){
            smallest = right;
        }
        if (smallest != i){
            //swap arr[i] , arr[smallest]
            int t = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = t;

            minHeapify(smallest);
        }
    }

    void maxHeapify(int i){
        int left = getLeftIndex(i);
        int right = getRightIndex(i);
        int largest = i;
        if (left < size && arr[left] > arr[largest]){
            largest = left;
        }

        if (right < size && arr[right] > arr[largest]){
            largest = right;
        }

        if (i != largest){
            // swap arr[i] & arr[largest]
            int t = arr[i];
            arr[i] = arr[largest];
            arr[largest] = t;

            maxHeapify(largest);
        }
    }

    int extractMin(){
        if (size == 0) return Integer.MAX_VALUE;
        else if (size == 1) {
            size--;
            return arr[size];
        } else{
            //swap arr[0] && arr[size-1]
            int t = arr[0];
            arr[0] = arr[size-1];
            arr[size-1] = t;

            size--;

            minHeapify(0);
            return arr[size];
        }

    }

    void decreaseKey(int i, int key){
        arr[i] = key;

        while (i != 0 && arr[parentIndex(i)] > arr[i]){
            //swap
            int t = arr[i];
            arr[i] = arr[parentIndex(i)];
            arr[parentIndex(i)] = t;

            i = parentIndex(i);
        }
    }

    void deleteElementAtIndex(int i){
        decreaseKey(i, Integer.MIN_VALUE);
        extractMin();
    }

    void buildHeap() {
        for (int i = (size - 2) / 2; i>=0; i--){
            minHeapify(i);
        }
    }

    public static void main(String[] args) {

        BinaryHeap heap = new BinaryHeap(10);
        int [] temp = { 10, 15, 25, 100, 20, 5, 100};
        for (int x: temp){
            heap.insertMinHeap(x);
        }
        heap.buildHeap();
//        System.out.println(heap.arr[0]);

//        System.out.println(heap.getLeft(0));
        System.out.println(heap.extractMin());

        heap.decreaseKey(1,2);

        heap.deleteElementAtIndex(1);

    }
}

class HeapProblems {
    public static void main(String[] args) {
        int[] arr = {5,15,1,3};
        medianOfAStream(arr);
    }

    static void medianOfAStream(int[] arr) {
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); //max heap
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int i = 0;i < arr.length; i++){
            insert(arr[i], left, right);
        }
    }

    private static void insert(int x, PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == 0 || x <= left.peek()){
            left.add(x);
        } else {
            right.add(x);
        }

        if (Math.abs(left.size() - right.size()) > 1){
            balance(left, right);
        }
        System.out.println(median(left,right));
    }

    private static void balance(PriorityQueue<Integer> left, PriorityQueue<Integer> right){
        if (Math.abs(left.size() - right.size()) > 1){
            if (left.size() > right.size()){
                right.add(left.remove());
            }else{
                left.add(right.remove());
            }
        }
    }

    private static double median(PriorityQueue<Integer> left, PriorityQueue<Integer> right){
        if (left.size() == right.size() && right.size() > 0){
            return (left.peek() + right.peek()) / 2.0;
        }else{
            if (left.size() > right.size()){
                return left.peek();
            }else {
                return right.peek();
            }
        }
    }
}
