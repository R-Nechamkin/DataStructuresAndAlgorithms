package hashTable;

import hashTable.wordCounterMap.WordCounterMap;

public class NaiiveWordCounter extends WordCounterMap {
    /**
     * This method takes in a string and hashes it
     *
     * @param str
     * @return
     */
    @Override
    protected int hash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash += str.charAt(0);
        }
        return hash;
    }
}
