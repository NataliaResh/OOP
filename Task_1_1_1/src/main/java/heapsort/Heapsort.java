package heapsort;

/**
 * Class for heapsort.
 */
public class Heapsort {
    /**
     * Sorts array of integers using heapsort.
     *
     * @param array array of integers.
     */
    static void heapsort(int[] array) {
        Heap.heapify(array);
        int len = array.length;
        for (int i = len - 1; i > 0; i--) {
            Heap.swap(array,0, i);
            Heap.siftDown(array, i, 0);
        }
    }
}
