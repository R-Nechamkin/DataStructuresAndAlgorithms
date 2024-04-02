package hashTable;

import hashTable.wordCounterMap.WordCounterMap;

import java.util.function.Function;

/**
 * This version of the Word-Counter Map uses the automatic hash-code function which comes with the {@code String} class.
 * It will be used for the purpose of testing the abstract class.
 */
public class JavaHashWordCounter extends WordCounterMap {

    /**
     * The hash method of this class simply calls the {@code .hashCode()} method on {@code str}.
     * @return
     */

    public JavaHashWordCounter(){
        super(String::hashCode);
    }

    public JavaHashWordCounter(int capacity, double loadFactor, boolean increaseCapacity, Function<String, Integer> hashFunction) {
        super(capacity, loadFactor, increaseCapacity, hashFunction);
    }
}
