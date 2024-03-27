package BinarySearchTree.stdOutBst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import BinarySearchTree.BSTNode;
import BinarySearchTree.BSTInterface;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    static BinarySearchTree<String> strings;

    @BeforeEach
    void setUp() {
        strings = new BinarySearchTree<String>();
    }

    @Test
    void AddingFirstElementWorks(){
        final String s = "aaaa";
        strings.add(s);
        Assertions.assertEquals(s, strings.root.getData());
    }

    @Test
    void AfterAddFirstElementContainsReturnsTrueForThatElement(){
        final String s = "aaaa";
        strings.add(s);
        assertTrue(strings.contains(s));
    }

    @Test
    void AddWithMultipleElementsAddsThemAllToTheTree(){
        String[] array = {"AAAA", "BBBB"};
        Arrays.sort(array);

        for(String s: array)
            strings.add(s);
        assertTrue(elementIsWithinFirstTwoLevelsOfTheArray(strings, array[0]));
        assertTrue(elementIsWithinFirstTwoLevelsOfTheArray(strings, array[1]));
    }

    /**
     * This method manually checks whether a given String is within the first two levels of
     *  a given String tree.
     * @return true if it is and false if it is not
     */
    boolean elementIsWithinFirstTwoLevelsOfTheArray(BinarySearchTree<String> tree, String value){
        BSTNode<String> root = tree.root;
        if(root == null)
            return false;
        if(root.getData().equals(value))
            return true;
        if (root.getLeft() != null) {
            if (root.getLeft().getData().equals(value))
                return true;
        }
        if (root.getRight() != null) {
            if (root.getRight().getData().equals(value))
                return true;
        }
        return false;
    }


    /**
     * This method manually checks whether a given String is within the first three levels of
     *  a given String tree (I.e.: The first 7 nodes).
     * <br> The code is incredibly unclean and unconsolidated in order to make sure there are no bugs.
     * @return true if it is and false if it is not
     */
    boolean elementIsWithinFirstThreeLevelsOfTheArray(BinarySearchTree<String> tree, String value){
        if(tree == null)
            return false;

        BSTNode<String> curr = tree.root;
        if(curr == null)
            return false;
        if(curr.getData().equals(value))
            return true;

        curr = curr.getLeft();
        if (curr != null) {
            if (curr.getData().equals(value))
                return true;

            if(curr.getLeft() != null){
                if (curr.getData().equals(value))
                    return true;
            }

            if(curr.getRight() != null){
                if (curr.getData().equals(value))
                    return true;
            }
        }

        curr = tree.root.getRight();
        if (curr != null) {
            if (curr.getData().equals(value))
                return true;

            if(curr.getLeft() != null){
                if (curr.getData().equals(value))
                    return true;
            }

            if(curr.getRight() != null){
                if (curr.getData().equals(value))
                    return true;
            }
        }

        return false;
    }

    @Test
    void AddWithMultipleElementsAddsThemInSortedOrder(){
        String[] array = {"AAAA", "BBBB"};
        Arrays.sort(array);

        for(String s: array)
            strings.add(s);
        Assertions.assertEquals(array[0], strings.root.getData());
        Assertions.assertEquals(array[1], strings.root.getRight().getData());
    }

    @Test
    void AfterAddMultipleElementsContainsReturnsTrueForAllOfThem(){
        String[] array = { "Hello", "how", "are", "you", "on", "this", "very", "fine", "day"};
        for(String s: array)
            strings.add(s);
        for (int i = 0; i < array.length; i++) {
            assertTrue(strings.contains(array[i]), "Could not find element " + i + " : " + array[i]);
        }
    }

    @Test
    void AfterAddMultipleEqualElementsContainsReturnsTrueForAllOfThem(){
        String[] array = new String[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = "jump";
        }
        for(String s: array)
            strings.add(s);
        for (int i = 0; i < array.length; i++) {
            assertTrue(strings.contains(array[i]), "Could not find element " + i + " : " + array[i]);
        }
    }

    @Test
    void MinWorksStandardCase(){
        String[] array = { "Hello", "how", "are", "you", "on", "this", "very", "fine", "day"};
        for(String s: array)
            strings.add(s);
        Arrays.sort(array);

        assertEquals(array[0], strings.min());
    }

    @Test
    void MaxWorksStandardCase(){
        String[] array = { "Hello", "how", "are", "you", "on", "this", "very", "fine", "day"};
        for(String s: array)
            strings.add(s);
        Arrays.sort(array);

        assertEquals(array[array.length -1], strings.max());
    }

    @Test
    void AfterRemoveIsCalledOnElementElementIsNoLongerInTree(){
        String[] array = { "Hello", "how", "are", "you", "on"};
        for(String s: array){
            strings.add(s);
        }

        final String element = array[3];
        strings.remove(element);
        assertFalse(elementIsWithinFirstThreeLevelsOfTheArray(strings, element));
    }

    @Test
    void StandardIteratorReturnsSortedElements(){
        String[] array = { "Hello", "how", "are", "you", "on", "this", "wonderful", "day"};
        for(String s: array){
            strings.add(s);
        }

        String[] temp = new String[array.length];
        int i = 0;
        for(String s: strings){
            temp[i++] = s;
        }

        Arrays.sort(array);
        assertArrayEquals(array, temp);
    }


    @Test
    void InOrderIteratorReturnsElementsInOrder(){
        String[] array = { "Hello", "how", "are", "you", "on", "this", "wonderful", "day"};
        for(String s: array){
            strings.add(s);
        }

        String[] temp = new String[array.length];
        Iterator<String> iter= strings.getIterator(BSTInterface.Traversal.Inorder);
        int i = 0;
        while (iter.hasNext()){
            temp[i++] = iter.next();
        }

        Arrays.sort(array);
        assertArrayEquals(array, temp);
    }
}