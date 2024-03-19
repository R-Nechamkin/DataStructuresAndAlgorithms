package hashTable.wordCounterMap;

import Sorting.ComparatorSorter;
import Sorting.MergeSortSorter;
import hashTable.GoodHashWordCounter;
import hashTable.LinkedMapEntry;
import hashTable.NaiiveWordCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/*
 * This class tests the WordCounterMap by counting the words in a text file of a book
 * It is really a testing class, which is why it violates the principle of least privilege by having access to the
 *  map's protected variables
 */
public class ManualTest {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        WordCounterMap map = createMapByUserPreference(keyboard);

        File file = getFile(keyboard);

        try {
            System.out.println("Reading words in file");
            countWordsInFile(file, map);
            int choice = choose(keyboard);
            while (choice != 5) {
                switch (choice) {
                    case 1:
                        lookUpWord(map, keyboard);
                        break;
                    case 2:
                        findLengthOfLinkedListOfWord(map, keyboard);
                        break;
                    case 3:
                        viewWordsInOrder(map);
                        break;
                    case 4:
                        internalReport(map);
                        break;
                    default:
                        break;
                }
                choice = choose(keyboard);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Sorry, your file wasn't found.");
            System.out.println("Exiting the program");
            System.exit(1);
        }

    }


    /**
     * This map asks the user if they would like to create a map with a complex hashing function or a naiive one.
     * @param keyboard  a {@code Scanner} object (The program assumes the Scanner is reading from the keyboard)
     * @return a WordCounterMap which either has a complex hashing function or a naiive one, depending on what the user
     *  has indicated they prefer
     */
    private static WordCounterMap createMapByUserPreference(Scanner keyboard) {
        if(askUserIfWantsComplexHashFunction(keyboard)){
            return new GoodHashWordCounter(10, 1.5, true);
        }
        else {
            return new NaiiveWordCounter(10, 1.5, true);
        }
    }

    private static boolean askUserIfWantsComplexHashFunction(Scanner keyboard){
        String question = "use a map with a complex hash function";
        String yesMeans = "create a map which uses a complex has function in order to reduce collisions";
        String noMeans = "use a map with a naiive hash function";
        return askYesNoQuestion(keyboard, question, yesMeans, noMeans);
    }

    /**
     * This method asks the user for the name of the file they wish to read.
     * If the user gives the name of a file which does not exist, the method continues asking the user for a name.
     * @param keyboard  A {@code Scanner}, which is assumed to refer to standard in.
     * @return  A file object created from the file the user offers.
     */
    private static File getFile(Scanner keyboard){
        System.out.println("Type the name of your file:");
        String fName = keyboard.nextLine();
        System.out.println("Opening file...");

        File f = new File(fName);
        while(!f.exists() || !f.isFile()){
            System.out.println("Sorry, I can't find a file by that name.");
            System.out.println("Please type the name of your file, making sure you spell it correctly.");
            f  = new File(keyboard.nextLine());
        }
        return f;
    }

    /**
     * This method goes through all words in a text file, and inserts them in the map
     * @param map   An instance of a {@code WordCounterMap} which will be used to count the words in the file
     * @param file  The text file to read
     */
    public static void countWordsInFile(File file, WordCounterMap map) throws FileNotFoundException {
        Scanner fIn = new Scanner(file);
        while (fIn.hasNext()){
            String word = fIn.next().toUpperCase();
            putAndIncrement(map, word, 1);
        }
    }


    public static int choose(Scanner input){
        System.out.println("What would you like to do? (Type the number of your choice.)");
        printOptions();

        String response = input.nextLine().substring(0,1);
        //Validate response
        while (!"12345".contains(response)){
            System.out.println("That is not a valid option. Please type 1, 2, 3, 4, or 5");
            if (askYesNoQuestion(input, "see the options again", "see the options again", "continue")) {
                System.out.println("These are the options.");
                printOptions();
            }
            response = input.nextLine();
        }
        return Integer.parseInt(response);
    }

    private static void printOptions(){
        System.out.println("1. See how many times a particular word appears.");
        System.out.println("2. See the length of the linked list which a word lies in.");
        System.out.println("3. View words in descending order according to word count.");
        System.out.println("4. View a report on the internal structure of the hash table.");
        System.out.println("5. Quit");
    }

    private static void lookUpWord(WordCounterMap map, Scanner keyboard){
        System.out.println("Type the word you wish to look up. ");
        String word = keyboard.next();
        System.out.println("'" + word + "' occurs " + map.getOrDefault(word, 0) + " times in this text.");
    }

    private static void findLengthOfLinkedListOfWord(WordCounterMap map, Scanner keyboard){
        System.out.println("Type the word you wish to look up. ");
        String word = keyboard.next();
        if(!map.contains(word)){
            System.out.println("The map does not contain the word '" + word + "'.");
        }
        else {
            int len = 0;
            int pos = 0;
            LinkedMapEntry<String, Integer> node = map.arr[map.getIndex(word)];
            while (node != null) {
                len ++;
                if(node.getKey().equals(word)){
                    pos ++;
                }
                node = node.getNext();
            }
            System.out.println("'" + word + "' is part of a linked list of length " + len +".");
            System.out.println("The word itself is number " + pos + " of the linked list. ");
        }
    }

    private static void viewWordsInOrder(WordCounterMap map){
        ComparatorSorter<LinkedMapEntry<String, Integer>> sorter =
                new MergeSortSorter<>((Comparator.comparingInt(o -> map.get(o.getKey()))));
        LinkedMapEntry<String, Integer>[] allElements = new LinkedMapEntry[map.size()];
        int i =0;
        for(LinkedMapEntry<String, Integer> element: map){
            allElements[i++] = element;
        }
   //     map.forEach(n -> allElements[i++] = n);
        sorter.sort(allElements);
        for(LinkedMapEntry<String, Integer> node: allElements){
            System.out.println(node.toString());
        }
    }

    private static void internalReport(WordCounterMap map){
        System.out.println("Number of words in book: " + map.size);
        System.out.println("\nNow we will show the internal structure of the hash table:\n");
        System.out.println("Size of backing array: " + map.arr.length);
        for(int i = 0; i < map.arr.length; i++){
            System.out.println("Slot " + arraySlotToString(map.arr[i]));
        }
        System.out.println("Num unused slots: " + getNumUnusedSlotsInArray(map));

    }


    private static int getNumUnusedSlotsInArray(WordCounterMap map){
        int unused =0;
        for (LinkedMapEntry<String, Integer> slot: map.arr){
            if(slot == null){
                unused ++;
            }
        }
        return unused;
    }

    /**
     * This method returns a string representation of what a slot in a map will look like
     * @param slot
     * @return
     */
    private static String arraySlotToString(LinkedMapEntry<String, Integer> slot){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while(slot != null){
            sb.append(slot);
            if(slot.hasNext()){
                sb.append(", ");
            }
            slot = slot.getNext();
        }
        sb.append("}");
        return sb.toString();
    }


    /**
     * This utility method asks the user if they would like to do something
     * @param keyboard  a {@code Scanner} object (The program assumes the Scanner is reading from the keyboard)
     * @param message   A String describing what the user is being asked to do
     * @param yMessage  A String describing what will happen if thet user indicates they wish to do the action
     * @param nMessage  A String describing what will happen if the user indicates they do not wish to do the action
     * @return {@code true} if the user wishes to do the action or {@code false} if the user does not
     */
    public static boolean askYesNoQuestion(Scanner keyboard, String message, String yMessage, String nMessage) {
        System.out.print("Would you like to " + message + "? (Y/N) ");
        char response = keyboard.nextLine().toUpperCase().charAt(0);
        while (response != 'Y' && response != 'N') {
            System.out.print(
                    "Error! You have entered an invalid response. Please type Y to " + nMessage + " or N to " + nMessage + ". ");
            response = keyboard.nextLine().toUpperCase().charAt(0);
        }
        if (response == 'Y') {
            return true;
        }
        return false;
    }

    /**
     * This utility method places a key in the hash map with the value of {@code  amount}.
     * If {@code  key} is already in the map, it increments the value associated with {@code key} by {@code amount}.
     * <br/> In short, this code has the same effect as <br/>{@code map.put(key, map.getOrDefault(key, 0) + amount)} <br/>
     *  if {@code map} was an instance of {@link java.util.Map}
     * @param map
     * @param word
     * @param amount
     * @return {@code true} if the incrementation is successful;
     * {@code false} if the key already exists and is associated with a value of Integer.MAX_VALUE
     */
    private static void putAndIncrement(WordCounterMap map, String word, int amount){
        map.put(word, map.getOrDefault(word, 0) + amount);
    }

}
