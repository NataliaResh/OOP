package sys.pro;

import java.util.*;

public class HashTable<K, V> implements Iterable<Pair<K, V>> {
    private static final double FACTOR = 0.75;
    private int capacity = 32;
    private int size = 0;
    private LinkedList<Pair<K, V>>[] hashTable = new LinkedList[capacity];

    public int getSize() {
        return size;
    }

    private void rehash() {
        capacity *= 2;
        LinkedList<Pair<K, V>>[] newHashTable = new LinkedList[capacity];
        for (Pair<K, V> pair: this) {
            int hash = getHash(pair.key);
            newHashTable[hash].add(pair);
        }
        hashTable = newHashTable;
    }

    public void put(K key, V value) {
        if ((double) size / capacity < FACTOR) {
            rehash();
        }
        int hash = getHash(key);
        hashTable[hash].add(new Pair<>(key, value));
        size++;
    }

    private int getHash(K key) {
        return key.hashCode() % capacity;
    }

    private Pair<K, V> find(K key) {
        int hash = getHash(key);
        for (Pair<K, V> pair : hashTable[hash]) {
            if (pair.key == key) {
                return pair;
            }
        }
        return null;
    }

    public V get(K key) {
        Pair<K, V> pair = find(key);
        if (pair != null) {
            return pair.value;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void updateValue(K key, V value) {
        Pair<K, V> pair = find(key);
        if (pair != null) {
            pair.value = value;
        }
    }

    @Override
    public String toString() {
        return "";
    }

    public void remove(K key) {
        int hash = getHash(key);
        Pair<K, V> pair = find(key);
        hashTable[hash].remove(pair);
    }

    public V forEach() {
        return null;
    }


    public boolean equal(HashTable<K, V> map) {
        return true;
    }

    public HashTable() {
        HashMap<Integer, Integer> map = new HashMap<>();

    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new HTIterator();
    }

    private class HTIterator implements Iterator<Pair<K, V>> {
        private int index = 0;
        private int count = 0;
        private Iterator<Pair<K, V>> chainIterator = Collections.emptyIterator();

        @Override
        public boolean hasNext() {
            return count < getSize();
        }

        @Override
        public Pair<K, V> next() {
            count++;
            if (chainIterator.hasNext()) {
                return chainIterator.next();
            }
            while (!chainIterator.hasNext()) {
                index++;
                if (index >= capacity) {
                    throw new NoSuchElementException("No more elements in this word!");
                }
                chainIterator = hashTable[index].iterator();
            }
            return chainIterator.next();
        }
    }
}
