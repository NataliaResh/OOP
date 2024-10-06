package sys.pro;

import java.util.*;

public class HashTable<K, V> implements Iterable<Pair<K, V>> {
    private static final double FACTOR = 0.75;
    private int capacity = 4;
    private int size = 0;
    private LinkedList<Pair<K, V>>[] hashTable;

    public int getSize() {
        return size;
    }

    private LinkedList<Pair<K, V>>[] initHashTable() {
        LinkedList<Pair<K, V>>[] hashTable = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            hashTable[i] = new LinkedList<>();
        }
        return hashTable;
    }

    private void rehash() {
        capacity *= 2;
        LinkedList<Pair<K, V>>[] newHashTable = initHashTable();
        for (Pair<K, V> pair : this) {
            int hash = getHash(pair.key);
            newHashTable[hash].add(pair);
        }
        hashTable = newHashTable;
    }

    public void put(K key, V value) {
        if (capacity / (double) size < FACTOR) {
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
        StringBuilder str = new StringBuilder();
        for (Pair<K, V> pair : this) {
            str.append(pair.key).append(" : ").append(pair.value).append("\n");
        }
        return str.toString();
    }

    public void remove(K key) {
        int hash = getHash(key);
        Pair<K, V> pair = find(key);
        hashTable[hash].remove(pair);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        HashTable<K, V> other = (HashTable<K, V>) obj;
        if (getSize() != other.getSize()) {
            return false;
        }
        for (Pair<K, V> pair : other) {
            if (!containsKey(pair.key)) {
                System.out.println(pair.key);
                return false;
            }
            if (!this.get(pair.key).equals(pair.value)) {
                System.out.println(get(pair.key));
                System.out.println(pair.value);
                return false;
            }
        }
        return true;
    }

    public HashTable() {
        hashTable = initHashTable();
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
                if (index >= capacity) {
                    throw new NoSuchElementException("No more elements in this word!");
                }
                chainIterator = hashTable[index].iterator();
            }
            index++;
            return chainIterator.next();
        }
    }
}
