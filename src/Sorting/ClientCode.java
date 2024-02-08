package Sorting;

import java.util.Random;
import java.util.Scanner;

public class ClientCode {
    public static void main(String[] args) {
        Student[] students = createStudentArray();
        getGrades(students);
        System.out.println("This is the unsorted array: \n" + studentArrayToString(students));

        sortWithQuickSort(students);
        sortWithMergeSort(students);
    }


    public static Student[] createStudentArray(){
        String[] names = {"Alice", "Beth", "Carol", "Debra", "Evelyn", "Fiona",
                "Gregory", "Harold", "Irene", "Judith", "Kalisha", "Leo"};
        int arraySize = getArraySizeFromStdIn();
        Student[] students = new Student[arraySize];
        for (int i = 0; i < arraySize; i++) {
            if (i < names.length) {
                students[i] = new Student(names[i]);
            } else {
                String fakeName = "Student number " + i;
                students[i] = new Student(fakeName);
            }
        }
        return students;
    }

    public static int getArraySizeFromStdIn() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How large a list of students would you like? (Type a positive integer): ");
        int size = keyboard.nextInt();
        while (size <= 0) {
            System.out.println("You typed " + size + "." +
                    " Please type the amount of students you would like in the list (Type a positive integer): ");
            size = keyboard.nextInt();
        }
        return size;
    }

    public static void getGrades(Student[] studentArr){
        if(doesUserWantToInputGradesForStudents()){
            getGradesFromStdIn(studentArr);
        }
        else {
            generateRandomGrades(studentArr);
        }
    }

    /**
     * This method asks the users to input grades.
     * <br>It interacts with the user by printing things out to and reading from the console.
     * @param arr   An array of students for which the user will input the grades
     */
    public static void getGradesFromStdIn(Student[] arr){
        Scanner keyboard = new Scanner(System.in);
        for(Student s: arr){
            double grade = getGradeFromStdIn(keyboard, s.name);
            s.setGrade(grade);
        }
    }

    /**
     * This method asks the user to input a specific grade.
     * <br>It interacts with the user by printing things out to and reading from the console.
     * @param keyboard  A {@code Scanner} object (The program assumes the Scanner is reading from the keyboard)
     * @param studentIdentifier   A String holding some identifier for the student
     * @return
     */
    public static double getGradeFromStdIn(Scanner keyboard, String studentIdentifier){
        System.out.println("Please input the grade for student: " + studentIdentifier);
        double grade = keyboard.nextDouble();
        while (grade < 0 || grade > 100){
            System.out.println("Grade must be above 0 and below 100. Input the grade for " + studentIdentifier);
            grade = keyboard.nextDouble();
        }
        return grade;
    }

    /**
     * This method generates random grades for all students in an array of students.
     * @param students An array of {@code Student} objects
     */
    public static void generateRandomGrades(Student[] students){
        Random rand = new Random();
        for(Student s: students){
            s.setGrade(rand.nextDouble()*100);
        }
    }

    /**
     * This method asks the user whether they want to input the grades for the students.
     * <br>It interacts with the user by printing things out to and reading from teh console.
     * @return {@code true} If the user wants to input the grades and {@code false} if they don't
     */
    public static boolean doesUserWantToInputGradesForStudents(){
        Scanner keyboard = new Scanner(System.in);
        String question = "input your own grades";
        String whatYesMeans = "type in grades for each of the students";
        String whatNoMeans = "let grades be randomly generated";
        return askYesNoQuestion(keyboard, question, whatYesMeans, whatNoMeans);
    }

    /**
     * This message asks the user if they would like to do something
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
     * This mehtod returns a String representation of an array of {@code Students}
     * @param array An array of {@code Student} objects
     * @return A String representation of the array
     */
    public static String studentArrayToString(Student[] array){
        StringBuilder sb = new StringBuilder();
        sb.append("{" + array[0]);
        for (int i = 1; i< array.length; i++){
            sb.append("," + array[i]);
        }
        sb.append("}");
        return sb.toString();
    }

    public static void sortWithQuickSort(Student[] students){

        ComparatorSorter<Student> sorter = new QuickSortSorter<>();
        Student[] sorted = sorter.sort(students);
        System.out.println("This is the sorted array: \n" + studentArrayToString(sorted));

        System.out.println();
        System.out.println("This is how the Quick Sort sorter sorted it: \n\n" + sorter.getLastSortDescription());

    }

    public static void sortWithMergeSort(Student[] students){

        ComparatorSorter<Student> sorter = new MergeSortSorter<>();
        Student[] sorted = sorter.sort(students);
        System.out.println("This is the sorted array: \n" + studentArrayToString(sorted));

        System.out.println();
        System.out.println("This is how the Merge Sort sorter sorted it: \n\n" + sorter.getLastSortDescription());

    }
}
