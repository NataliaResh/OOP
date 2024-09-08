package heapsort;

import java.util.Arrays;

/**
 * Main class.
 */
public class Main {
    /**
     * Main function (start of program).
     * @param args arguments of command line.
     */
    public static void main(String[] args) {
        int[] array = {2, 3, 1};
        Heapsort.heapsort(array);
        System.out.println(Arrays.toString(array));
    }
}
