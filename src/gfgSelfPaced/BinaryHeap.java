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
