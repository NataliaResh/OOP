public class Heapsort {

    static void heapsort(int[] array) {
        Heap heap = new Heap(array);
        int len = array.length;
        for (int i = len - 1; i > 0; i--) {
            heap.swap(0, i);
            heap.shiftDown(i, 0);
        }
    }
}
