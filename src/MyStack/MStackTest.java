package MyStack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MStackTest {
    static MStack<String> strings;

    @BeforeEach
    void setUp() {
        strings = new MStack<>();
    }

    @Test
    void PushWorksOneElement(){
        String toPush = "String number 1";
        strings.push(toPush);
        assertEquals(strings.head.data, toPush);
    }

    @Test
    void PopReturnsWhatWasPushedOneElement(){
        String toPush = "String number 1";
        strings.push(toPush);
        assertEquals(strings.pop(), toPush);
    }

    @Test
    void IsEmptyReturnsTrueForNewStack(){
        assertTrue(strings.isEmpty());
    }

    @Test
    void isEmptyReturnsTrueWhenAddedAndRemovedMultipleElements(){
        String[] array = "Hello i am happy to see you right now".split(" ");
        for(String s: array)
            strings.push(s);
        for (int i = 0; i < array.length; i++) {
            strings.pop();
        }
        assertTrue(strings.isEmpty());
    }

    @Test
    void sizeReturnsCorrectSize(){
        String[] array = "Hello i am happy to see you right now".split(" ");
        for(String s: array)
            strings.push(s);
        assertEquals(array.length, strings.size());
    }
}