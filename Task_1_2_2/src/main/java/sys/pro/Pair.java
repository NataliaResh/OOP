package sys.pro;

/**
 * Class for managing Pair.
 *
 * @param <K> type of key;
 * @param <V> type of value.
 */
public class Pair<K, V> {
    public K key;
    public V value;

    /**
     * Constructor of Pair.
     *
     * @param key   key;
     * @param value value.
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
