/**
 * Class for heapsort
 */
public class Heapsort {
    /**
     * Sorts array of integers using heapsort
     * @param array array of integers
    */
    static void heapsort(int[] array) {
        Heap heap = new Heap(array);
        int len = array.length;
        for (int i = len - 1; i > 0; i--) {
            heap.swap(0, i);
            heap.siftDown(i, 0);
        }
    }
}
