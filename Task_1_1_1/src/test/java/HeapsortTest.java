import org.junit.jupiter.api.Test;

import org.testng.Assert;

public class HeapsortTest {
    @Test
    public void emptyArray() {
        int[] array = {};
        int[] result = {};
        Heapsort.heapsort(array);
        Assert.assertEquals(array, result);
    }

    @Test
    public void oddLenArray() {
        int[] array = {2, 1, 5, 4, 3};
        int[] result = {1, 2, 3, 4, 5};
        Heapsort.heapsort(array);
        Assert.assertEquals(array, result);
    }

    @Test
    public void evenLenArray() {
        int[] array = {2, 6, 1, 5, 4, 3};
        int[] result = {1, 2, 3, 4, 5, 6};
        Heapsort.heapsort(array);
        Assert.assertEquals(array, result);
    }

    @Test
    public void reverseArray() {
        int[] array = {7, 6, 5, 4, 3, 2, 1};
        int[] result = {1, 2, 3, 4, 5, 6, 7};
        Heapsort.heapsort(array);
        Assert.assertEquals(array, result);
    }

    @Test
    public void sortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] result = {1, 2, 3, 4, 5};
        Heapsort.heapsort(array);
        Assert.assertEquals(array, result);
    }

    @Test
    public void repeatArray() {
        int[] array = {2, 2, 1, 2, 3, 1, 0, 5, -2, 2};
        int[] result = {-2, 0, 1, 1, 2, 2, 2, 2, 3, 5};
        Heapsort.heapsort(array);
        Assert.assertEquals(array, result);
    }
}
