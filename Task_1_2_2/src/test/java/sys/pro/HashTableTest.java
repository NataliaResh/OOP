package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.HashMap;

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
    public void toStringHashTableTest() {
        HashTable<Integer, String> map = new HashTable<>();
        map.put(1, "one");
        map.put(3, "three");
        map.put(2, "two");
        Assert.assertEquals(map.toString(), "1 : one\n2 : two\n3 : three\n");
    }
}
