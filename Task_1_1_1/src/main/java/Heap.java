/**
 * Class for manage max-heap.
 */
public class Heap {
    /**
     * Private field {@code heap}.
     */
    int[] heap;

    /**
     * Constructor of new object.
     *
     * @param array array what be heapified.
     */
    public Heap(int[] array) {
        heap = array;
        heapify();
    }

    /**
     * Converts array {@link Heap#heap} to max-heap.
     */
    private void heapify() {
        int len = heap.length;
        int start = len / 2;
        for (int i = start; i >= 0; i--) {
            siftDown(len, i);
        }
    }

    /**
     * Swaps two elements in array {@link Heap#heap}.
     *
     * @param i first index.
     * @param j second index.
     */
    public void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Sifts down element on position {@code index}.
     *
     * @param len   current len of heap.
     * @param index index of element.
     */
    public void siftDown(int len, int index) {
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
            siftDown(len, largest);
        }
    }
}
