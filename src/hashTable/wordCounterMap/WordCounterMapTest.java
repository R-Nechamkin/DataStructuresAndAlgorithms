package hashTable.wordCounterMap;

import hashTable.JavaHashWordCounter;
import hashTable.LinkedMapEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterMapTest {

    WordCounterMap map;
    String[] array = { "Hello", "how", "are", "you", "on", "this", "very", "fine", "day"};

    @BeforeEach
    void setUp(){
        map =  new JavaHashWordCounter();
    }

    @Test
    void AfterPutOneWordInMapWordIsThere() {
        String word = "Hello";
        map.put(word, 0);
        assertTrue(mapContainsKey(word));
    }

    @Test
    void AfterPutMultipleWordsInMapAllAreThere(){
        for(String s: array)
            map.put(s, 1);
        for (int i = 0; i < array.length; i++) {
            assertTrue(mapContainsKey(array[i]), "Could not find element " + i + " : " + array[i]);
        }
    }

    @Test
    void AfterPutMultipleWordsContainsReturnsTrueForAll(){
        for(String s: array)
            map.put(s, 1);
        for (int i = 0; i < array.length; i++) {
            assertTrue(map.contains(array[i]), "Could not find element " + i + " : " + array[i]);
        }
    }

    @Test
    void AfterPutSingleElementGetReturnsCorrectValue(){
        String word = "Hello";
        int val = 7;

        map.put(word, val);
        assertEquals(val, map.get(word));
    }

    @Test
    void AfterPutMultipleElementsGetReturnsCorrectValueForAll(){
        Random rand = new Random();
        int[] vals = new int[array.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = rand.nextInt();
        }
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], vals[i]);
        }
        for (int i = 0; i < array.length; i++) {
            assertEquals(vals[i], map.get(array[i]), "Map did not return correct value for element " + i + ".");
        }
    }

    @Test
    void AfterPutSingleElementAndRemoveItElementIsNoLongerInMap(){
        String word = "Hello";

        map.put(word, 1);
        map.remove(word);
        assertFalse(mapContainsKey(word));
    }


    @Test
    void AfterPutMultipleElementsAndRemoveOneThatOneIsNoLongerInMap(){
        for(String s: array)
            map.put(s, 1);
        map.remove(array[3]);
        assertFalse(mapContainsKey(array[3]));
    }

    /**
     * This test places multiple elements in the map, and then removes one.
     * It then checks that the removed key is no longer in the map, <strong>and</strong> that
     *  the other keys are still in the map.
     */
    @Test
    void PutMultipleElementsAndRemoveOneOnlyThatOneIsNoLongerInMap(){
        for(String s: array)
            map.put(s, 1);

        int indexToRemove = 3;
        map.remove(array[indexToRemove]);
        for (int i = 0; i < array.length; i++) {
            if(i == indexToRemove){
                assertFalse(mapContainsKey(array[indexToRemove]), array[indexToRemove] + " was not removed.");
            }
            else {
                assertTrue(mapContainsKey(array[i]));
            }
        }
    }

    @Test
    void SizeReturnsOneAfterAddOneElement(){
        String word = array[0];
        map.put(word, 9);
        assertEquals(1, map.size());
    }
    
    @Test
    void AfterAddMultipleElementsSizeReturnsCorrectNum(){
        for (String s: array){
            map.put(s, 85);
        }
        assertEquals(array.length, map.size());
    }
    
    @Test
    void AfterAddAndRemoveMultipleElementsSizeReturnsCorrectNum(){
        for (String s: array){
            map.put(s, 85);
        }
        int amountRemoved = 0;
        for (int i = 0; i < array.length; i+=2) {
            map.remove(array[i]);
            amountRemoved ++;
        }
        assertEquals(array.length - amountRemoved, map.size());
    }

    @Test
    void BeforeAddAnyElementsIsEmptyReturnsTrue(){
        assertTrue(map.isEmpty());
    }

    @Test
    void AfterAddElementsAndRemoveThemAllIsEmptyReturnsTrue(){
        for (String s: array){
            map.put(s, 85);
        }
        for(String s: array){
            map.remove(s);
        }
        assertTrue(map.isEmpty());
    }


    @Test
    void CapacityIncreasesWhenPutIsUsed(){
        String[] longArray = "Alpha beta gamma delta ae bee cee dee ee ef gee haich ie jay kay el em en oh pee que are es tee you vee doubleyou ex why zee ".split(" ");
        int size = longArray.length;
        for (int i = 0; i < longArray.length; i++) {
            map.put(longArray[i], 5);
        }
        assertEquals(40, map.arr.length);
    }

    @Test
    void IteratorContainsAllElements() throws FileNotFoundException {
        final int ARR_SIZE = 100;
        String[] arrayFromFile = new String[ARR_SIZE];
        String fName = "C:\\Users\\rnech\\IdeaProjects\\MoreDataStructures\\src\\hashTable\\textFiles\\Book1.txt";
        File f = new File(fName);
        Scanner fScanner = new Scanner(f);
        for (int i = 0; i < ARR_SIZE; i++) {
            arrayFromFile[i] = fScanner.next().toUpperCase();
            map.put(arrayFromFile[i], 12);
        }

        List<String> fromIterator = new ArrayList<>(map.size());
        for (LinkedMapEntry<String, Integer> node: map){
            fromIterator.add(node.getKey());
        }

        for (int i = 0; i < ARR_SIZE; i++) {
            assertTrue(fromIterator.contains(arrayFromFile[i]),
                    "Element " + i + " : '" + arrayFromFile[i] + "' was not found.");
        }
    }

    /**
     * This is a utility method which manually goes through the array associated with the map,
     * checking whether for any element e in the array, it is true that e.getKey() will be equal to {@code str}.
     * @param str
     * @return
     */
    boolean mapContainsKey(String str){
        for(LinkedMapEntry e: map.arr) {
            if (e != null) {
                if (e.getKey().equals(str)) {
                    return true;
                }
                while (e.hasNext()) {
                    e = e.getNext();
                    if (e != null && e.getKey().equals(str)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}