package hashTable;

import hashTable.wordCounterMap.WordCounterMap;

/**
 * This version of the Word-Counter Map uses the automatic hash-code function which comes with the {@code String} class.
 * It will be used for the purpose of testing the abstract class.
 */
public class JavaHashWordCounter extends WordCounterMap {

    /**
     * The hash method of this class simply calls the {@code .hashCode()} method on {@code str}.
     * @param str
     * @return
     */
    @Override
    protected int hash(String str) {
        return str.hashCode();
    }
}
