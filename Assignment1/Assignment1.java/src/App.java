//Daniel Mulangu Kaseya
//72826452
//dkaseya@unomaha.edu
//question a

import java.util.*;


public class App {

    public static void main(String[] args) {
        int n = 50000; // total number of elements to be partitioned
        int upper_bound=500; // maximum value for each element in the array
        int[] data_array = generate_random_array(n, upper_bound);//Generated the array using the total size and  upper bound
        int k=100;
        
        // Find the best possible partition using all possible binary arrays
        partition(data_array,k);
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

    // Finding the best partition using all possible binary arrays
    private static void partition(int[] data,int k) {
        int n = data.length;
        int[] best_subset1 = null;
        int[] best_subset2 = null;
        int minimum_diff = Integer.MAX_VALUE;
        int s1=0,s2=0;

        for (int i = 0; i < (1 << n); i++) {
            int[] binary_array = generate_binary_array(i, n);

            int[] subset1 = get_subset(data, binary_array, true);
            int[] subset2 = get_subset(data, binary_array, false);

            int sum1 = find_sum(subset1);
            int sum2 = find_sum(subset2);
           
            int current_diff = Math.abs(sum1 - sum2);//current difference

            if (current_diff < minimum_diff) {
                minimum_diff = current_diff;
                best_subset1 = subset1;
                best_subset2 = subset2;
                s1=sum1;
                s2=sum2;
            }
        }
        if(minimum_diff<=k){
            System.out.print(" ");
            System.out.println("Partition Possible");
             // Print the best partition and sums// Uncomment line below to see the best partition 
            //System.out.println("Best Partition: " + Arrays.toString(best_subset1) + " | " + Arrays.toString(best_subset2));
            System.out.println("Min Difference: " + minimum_diff);
            System.out.println("The minimum sum of the partition: " + s1+" , "+s2);
            System.out.print(" ");
        }
        else{
            System.out.print(" ");
            System.out.println("Partition not Possible");
            //Uncomment line below to see the best partition
           // System.out.println("Best Partition: " + Arrays.toString(best_subset1) + " |  " + Arrays.toString(best_subset2));
            System.out.println("Min Difference: " + minimum_diff);
            System.out.println("The minimum sum of the partition " + s1+" , "+s2);
            System.out.print(" ");
        }
 
    }

    // Function to generate a binary array based on a decimal number
    private static int[] generate_binary_array(int num, int size) {
        int[] binary_array = new int[size];
        for (int i = 0; i < size; i++) {
            binary_array[size - 1 - i] = (num & (1 << i)) != 0 ? 1 : 0;
        }
        return binary_array;
    }

    // Get a subset of the input data based on the generated binary array and return the possible subset
    private static int[] get_subset(int[] data_size, int[] binary_array, boolean include) {
        int[] subset = new int[data_size.length];
        int subsetIndex = 0;
        for (int i = 0; i < data_size.length; i++) {
            if ((binary_array[i] == 1 && include) || (binary_array[i] == 0 && !include)) {
                subset[subsetIndex++] = data_size[i];
            }
        }
        return Arrays.copyOf(subset, subsetIndex);
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

