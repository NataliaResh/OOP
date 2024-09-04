public class Heapsort {

//    public static void main(String[] args) {
//        int[] a = heapsort(new int[] {2, 1, 3, 0});
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
//    }

    static void heapsort(int[] array) {
        Heap heap = new Heap(array);
        int len = array.length;
        for(int i = len - 1; i > 0; i--) {
            heap.swap(0, i);
            heap.shiftDown(i, 0);
        }
    }
}
