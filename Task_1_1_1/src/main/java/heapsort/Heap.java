package heapsort;

/**
 * Class for manage max-heap.
 */
public class Heap {
    /**
     * Converts array {@code array} to max-heap.
     *
     * @param array array that will be heapified.
     */
    public static void heapify(int[] array) {
        int len = array.length;
        int start = len / 2;
        for (int i = start; i >= 0; i--) {
            siftDown(array, len, i);
        }
    }

    /**
     * Swaps two elements in {@code array}.
     *
     * @param array array where will be swapping.
     * @param i first index.
     * @param j second index.
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Sifts down element in {@code heap} on position {@code index}.
     *
     * @param heap heap where will be sifting
     * @param len current len of heap.
     * @param index index of element.
     */
    public static void siftDown(int[] heap, int len, int index) {
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
            swap(heap, index, largest);
            siftDown(heap, len, largest);
        }
    }
}
