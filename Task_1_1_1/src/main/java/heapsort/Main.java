package heapsort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {2, 3, 1};
        Heapsort.heapsort(array);
        System.out.println(Arrays.toString(array));
    }
}
