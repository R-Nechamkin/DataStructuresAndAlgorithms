package hashTable;

import hashTable.wordCounterMap.WordCounterMap;


/**
 * This class implements the widely used function for string hashing.
 * I found the function at <a href="https://cp-algorithms.com/string/string-hashing.html">...</a>
 */
public class GoodHashWordCounter extends WordCounterMap {
    public GoodHashWordCounter() {
    }

    public GoodHashWordCounter(int capacity, double loadFactor, boolean increaseCapacity) {
        super(capacity, loadFactor, increaseCapacity);
    }

    final static int p = 53;

    @Override
    protected int hash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash += (int) (str.charAt(i) * Math.pow(p, i));
        }
        return hash;
    }
}
