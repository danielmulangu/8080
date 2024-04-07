//Daniel Mulangu Kaseya
//72826452
//dkaseya@unomaha.edu

import java.util.*;


public class App {

    public static boolean anagrams(String str1, String str2) {
        // Convert the strings into character arrays

        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        // Check if lengths of the two strings are equals
        if (charArray1.length != charArray2.length) {
            return false;
        }

        // Sort both  strings arrays using QuickSort
        quickSort(charArray1, 0, charArray1.length - 1);
        quickSort(charArray2, 0, charArray2.length - 1);

        // Compare the two sorted strings arrays and return false if there is any character difference
        for (int i = 0; i < charArray1.length; i++) {
            if (charArray1[i] != charArray2[i]) {
                return false;
            }
        }

        return true;
    }

    private static void quickSort(char[] arr, int low, int high) {
        if (low < high) {
            // Partition the array 
            int pivot = partition(arr, low, high);

            // Sort the elements before and after the pivot recursively
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private static int partition(char[] arr, int low, int high) {
        char pivot = arr[high];
        int i = low - 1;

        // Move elements smaller than pivot to the left
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] in order to put the pivot element in the correct place
        char temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {

        //Get the first string from the keyboard
        System.out.println("Enter First input  string:");
        Scanner scanner1=new Scanner(System.in);
        String str1=scanner1.nextLine();


        //Get the second screen from the keyboard 
        System.out.println("Enter the second string input:");
        Scanner scanner2= new Scanner(System.in);
        String str2 = scanner2.nextLine();

        if (anagrams(str1, str2)) {
            System.out.println("True: "+str1 + " and " + str2 + " are anagrams.");
        } else {
            System.out.println("False: "+str1 + " and " + str2 + " are not anagrams.");
        }
    }
}
