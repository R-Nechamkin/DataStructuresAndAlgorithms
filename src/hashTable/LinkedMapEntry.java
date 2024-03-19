package hashTable;

/**
 * This class can be used for iterating through maps, or for use in buckets-and-chaining.
 * @param <K>
 * @param <V>
 */
public class LinkedMapEntry<K, V> {
    private K key;
    private V value;
    private LinkedMapEntry<K,V> next;


    public LinkedMapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }


    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public LinkedMapEntry<K, V> getNext() {
        return next;
    }

    public void setNext(LinkedMapEntry<K, V> next) {
        this.next = next;
    }

    public boolean hasNext(){
        return getNext() != null;
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }

    
}
