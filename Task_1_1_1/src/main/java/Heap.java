public class Heap {
    int[] heap;


    public Heap(int[] array) {
        heap = array;
        heapify();
    }

    private void heapify() {
        int len = heap.length;
        int start = len / 2;
        for (int i = start; i >= 0; i--) {
            shiftDown(len, i);
        }
    }

    public void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    public void shiftDown(int len, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;
        if (left < len && heap[index] < heap[left]) {
            largest = left;
        }
        if (right < len && heap[largest] < heap[right]) {
            largest = right;
        }
        if (largest != index) {
            swap(index, largest);
            shiftDown(len, largest);
        }
    }
}
