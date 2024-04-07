//Daniel Mulangu Kaseya
//72826452
//dkaseya@unomaha.edu
//question b

import java.util.*;

public class partb {

    public static void main(String[] args) {
        int n = 50000; // total number of elements to be partitioned
        int upper_bound=100; // maximum value for each element in the array
        int[] data_array = generate_random_array(n, upper_bound);//Generated the array using the total size and upper bound
        
        // Find the best possible partition using all possible binary arrays
        greedy_partition(data_array);
    }

    // Generate array filled  with random integers between 0 and n
    private static int[] generate_random_array(int n, int limit) {
        int[] random_array = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            random_array[i] = random.nextInt(limit); // the range of tyhe values stored in the array
        }

        return random_array;
    }

     // Find the best possible partition using a greedy heuristic
     private static void greedy_partition(int[] data) {
        int n = data.length;
        Arrays.sort(data); // Sort the data in ascending order

        int[] best_subset1 = Arrays.copyOfRange(data, 0, n / 2);
        int[] best_subset2 = Arrays.copyOfRange(data, n / 2, n);
        //Divide the numbers into two sets going from 0 to n/2 and from n/2 to n

        int sum1 = find_sum(best_subset1);
        int sum2 = find_sum(best_subset2);

        // Print the best partition and sums //Uncomment line below to print the subsets 
        //System.out.println("Best Partition: " + Arrays.toString(best_subset1) + " | " + Arrays.toString(best_subset2));
        System.out.println("Min Difference: " + Math.abs(sum1 - sum2));
        System.out.println("The sum for the minimum: " + sum1 + " , " + sum2);
    }
    

    //Find the sum of the elements in the array
    private static int find_sum(int[] array) {
        int sum = 0;
        for (int element : array) {
            sum += element;
        }
        return sum;
    }

}
