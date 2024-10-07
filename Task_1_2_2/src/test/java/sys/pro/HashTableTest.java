package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing HashTable.
 */
public class HashTableTest {
    @Test
    public void createHashTableTest() {
        HashTable<Integer, String> map = new HashTable<>();
        map.put(1, "one");
        map.put(3, "three");
        map.put(2, "two");
        map.put(5, "five");
        map.put(4, "four");
        map.put(0, "zero");
        for (Pair<Integer, String> pair: map) {
            Assert.assertTrue(map.containsKey(pair.key));
            Assert.assertEquals(map.get(pair.key), pair.value);
        }
    }

    @Test
    public void removeTest() {
        HashTable<Integer, String> map = new HashTable<>();
        int n = 100;
        for (int i = 0; i < n; i++) {
            map.put(i, "a");
        }
        Assert.assertFalse(map.remove(n + 1));
        for (int i = 31; i < 70; i++) {
            Assert.assertTrue(map.remove(i));
        }
        for (int i = 0; i < n; i++) {
            if (i >= 31 && i < 70) {
                Assert.assertFalse(map.containsKey(i));
            } else {
                Assert.assertTrue(map.containsKey(i));
            }
        }
    }

    @Test
    public void toStringHashTableTest() {
        HashTable<Integer, String> map = new HashTable<>();
        map.put(1, "one");
        map.put(3, "three");
        map.put(2, "two");
        Assert.assertEquals(map.toString(), "1 : one\n2 : two\n3 : three\n");
    }

    @Test
    public void equalHashTableTest() {
        HashTable<Integer, Integer> firstMap = new HashTable<>();
        Assert.assertTrue(firstMap.equals(firstMap));
        HashTable<Integer, Integer> secondMap = null;
        Assert.assertFalse(firstMap.equals(secondMap));
        secondMap = new HashTable<>();
        int n = 1000;
        for (int i = 0; i < n; i++) {
            firstMap.put(i, n - i - 1);
            secondMap.put(n - i - 1, i);
        }
        Assert.assertTrue(firstMap.equals(secondMap));
        secondMap.put(n, 0);
        Assert.assertFalse(firstMap.equals(secondMap));
        secondMap.remove(n);
        Assert.assertTrue(firstMap.equals(secondMap));
        secondMap.updateValue(1, 42);
        Assert.assertFalse(firstMap.equals(secondMap));
    }

    @Test
    public void updateTest() {
        HashTable<Integer, String> map = new HashTable<>();
        int n = 100;
        for (int i = 0; i < n; i++) {
            map.put(i, "a");
        }
        for (int i = 0; i < n; i++) {
            Assert.assertEquals(map.get(i), "a");
            map.updateValue(i, "b");
            Assert.assertEquals(map.get(i), "b");
        }
    }
}
