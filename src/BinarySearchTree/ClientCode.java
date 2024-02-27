package BinarySearchTree;

import java.util.Iterator;
import java.util.Scanner;
import BinarySearchTree.stdOutBst.BinarySearchTree;
import BinarySearchTree.BSTInterface.Traversal;

public class ClientCode {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        BSTInterface<String> tree = new BinarySearchTree<>();
        TestTreeWrapper wrapper = new TestTreeWrapper(tree);

        insertData(wrapper, keyboard);
        boolean keepGoing;
        do {
            switch (choose(keyboard)){
                case 1:
                    addNode(wrapper, keyboard);
                    break;
                case 2:
                    removeNode(wrapper, keyboard);
                    break;
                case 3:
                    printTreeContents(wrapper, keyboard);
                    break;
                case 4:
                    seeIfTreeContainsSpecficElement(wrapper,keyboard);
                    break;
                case 5:
                    getMinElement(wrapper);
                    break;
                case 6:
                    getMaxElement(wrapper);
                    break;
                case 7:
                default:
                    break;
            }
            keepGoing = askIfWantsToContinue(keyboard);
        } while (keepGoing);

        System.out.println("Goodbye.");
    }




    public static void insertData(TestTreeWrapper wrapper, Scanner keyboard){
        if(askIfWantsToInsertData(keyboard)){
            String[] data = getDataFromConsole(keyboard);
            wrapper.insertArrayOfData(data);
        }
        else {
            wrapper.insertRandomData(50);
        }
    }

    public static boolean askIfWantsToInsertData(Scanner keyboard){
        String question = "insert your own data";
        String actionYesWillCause = "type a list of strings into the console";
        String actionNoWillCause = "have the computer generate a random list of strings for you";
        return askYesNoQuestion(keyboard, question, actionYesWillCause, actionNoWillCause);
    }

    public static String[] getDataFromConsole(Scanner keyboard){
        System.out.println("Enter your words as a comma-separated list into the console."
                            +"\nPress enter when done.");
        return keyboard.nextLine().split(",");
    }


    public static int choose(Scanner input){
        System.out.println("What would you like to do? (Type the number of your choice.)");
        printOptions();

        String response = input.nextLine().substring(0,1);
        //Validate response
        while (!"1234567".contains(response)){
            System.out.println("That is not a valid option. Please type 1, 2, 3, 4, 5, 6, or 7");
            if (askYesNoQuestion(input, "see the options again", "see the options again", "continue")) {
                System.out.println("These are the options.");
                printOptions();
            }
            response = input.nextLine();
        }
        return Integer.parseInt(response);
    }

    public  static void printOptions(){
        System.out.println("1. Add a string.");
        System.out.println("2. Remove a string ");
        System.out.println("3. Print out all the items.");
        System.out.println("4. See if the tree contains a specific string.");
        System.out.println("5. Get the smallest element in the tree.");
        System.out.println("6. Get the greatest element in the tree.");
        System.out.println("7. Quit the program.");
    }

    /**
     * Asks the user for a string and then attempts to add that string to the tree.
     * @param wrapper   A wrapper containg the tree you're using.
     * @param keyboard  A {@code Scanner} object (The program assumes the Scanner is reading from the keyboard)
     */
    public static void addNode(TestTreeWrapper wrapper, Scanner keyboard){
        System.out.print("Type the string you would like to add:");
        wrapper.add(keyboard.nextLine());
    }


    /**
     * Asks the user for a string and then attempts to remove that string to the tree.
     * @param wrapper   A wrapper containg the tree you're using.
     * @param keyboard  A {@code Scanner} object (The program assumes the Scanner is reading from the keyboard)
     */
    public static void removeNode(TestTreeWrapper wrapper, Scanner keyboard){
        System.out.print("Type the string you would like to remove:");
        boolean removed = wrapper.remove(keyboard.nextLine());
        if(removed){
            System.out.println("Successfully removed");
        }
        else {
            System.out.println("This string does not appear in the tree.");
        }
    }

    public static void printTreeContents(TestTreeWrapper wrapper, Scanner keyboard){
        Traversal traversalType = getTraversalType(keyboard);
        Iterator<String> itr = wrapper.getIterator(traversalType);

        System.out.println("Printing out all strings according to your traversal order");

        while (itr.hasNext()){
            System.out.println(itr.next());
        }

    }

    /**
     * This method asks the user to pick a traversal type
     * @param keyboard
     * @return
     */
    private static Traversal getTraversalType(Scanner keyboard){
        System.out.println("How would you like to iterate through the tree?" +
                "\n1. Preorder" +
                "\n2. Inorder" +
                "\n3. Postorder" +
                "\n4. Breadth-first");
        System.out.println("Type the number of your choice.");
        char response = keyboard.nextLine().charAt(0);
        while (response != '1' && response != '2' && response != '3' && response != '4'){
            System.out.println("That is not a valid option. Please type '1' for Preorder, '2' for Inorder, " +
                    "'3' for Postorder, or '4' for Breadth-first search");
            response = keyboard.nextLine().charAt(0);
        }

        if(response== '1'){
            return Traversal.Preorder;
        }
        else if(response=='2'){
            return Traversal.Inorder;
        }
        else if(response == '3'){
            return Traversal.Postorder;
        }
        else {
            return Traversal.BreadthFirst;
        }
    }

    /**
     * Asks the user for a string and then sees if the tree contains that string.
     * @param wrapper   A wrapper containg the tree you're using.
     * @param keyboard  A {@code Scanner} object (The program assumes the Scanner is reading from the keyboard)
     */
    public static void seeIfTreeContainsSpecficElement(TestTreeWrapper wrapper, Scanner keyboard){
        System.out.print("Type your string:");
        String str = keyboard.nextLine();
        if(wrapper.contains(str)){
            System.out.println("Tree contains your element.");
        }
        else {
            System.out.println("This string does not appear in the tree.");
        }
    }

    /**
     * Prints the smallest element in the tree
     * @param wrapper   A wrapper containg the tree you're using.
     */
    public static void getMinElement(TestTreeWrapper wrapper){
        String min = wrapper.min();
        System.out.println("The smallest element in the tree is " + min);
    }

    /**
     * Prints the largest element in the tree
     * @param wrapper   A wrapper containg the tree you're using.
     */
    public static void getMaxElement(TestTreeWrapper wrapper){
        String max = wrapper.max();
        System.out.println("The largest element in the tree is " + max);
    }


    public static boolean askIfWantsToContinue(Scanner keyboard){
        String question = "continue manipulating the tree";
        String actionYesWillCause = question;
        String actionNoWillCause = "end the program";
        return askYesNoQuestion(keyboard, question, actionYesWillCause, actionNoWillCause);

    }


    /**
     * This function asks the user if they would like to do something
     * @param keyboard  a {@code Scanner} object (The program assumes the Scanner is reading from the keyboard)
     * @param message   A String describing what the user is being asked to do
     * @param yMessage  A String describing what will happen if the user indicates they wish to do the action
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
}
