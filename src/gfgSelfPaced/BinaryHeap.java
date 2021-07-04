package gfgSelfPaced;

public class BinaryHeap {
    private final int[] arr;
    private final int capacity;
    private int size = 0;

    BinaryHeap(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
    }

    int getLeft(int i){
        return arr[i * 2 + 1];
    }

    int getRight(int i){
        return arr[i * 2 + 2];
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


    public static void main(String[] args) {

        BinaryHeap heap = new BinaryHeap(10);
        heap.insertMinHeap(19);
        heap.insertMinHeap(13);
        heap.insertMinHeap(20);
//        System.out.println(heap.arr[0]);

        System.out.println(heap.getLeft(0));
    }
}
