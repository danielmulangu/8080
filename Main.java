/*Project for Design and Analysis of Algorithms class CSCI8080 
 * Implementation of the genetic algorithm for trying to solve
 * the boolean satisifiability problem
 * Daniel Mulangu Kaseya
 * 72826452
 * dkaseya@unomaha.edu
 * daniel.mulangu@icloud.com
*/

import java.io.*;
import java.util.*;

public class Main {
    private static final int row=450;
    private static final int column =101;
    private static final int total_clause =403;
    private static final int clause_size =3;

    public static void main(String[] args){
     int [][] formula;
     formula =new int [total_clause][clause_size];
     int  i=0,j=0; //will be used when storing the formula from the cnf file into the 2D array
     int [][] population;

    population= new int [row][column];//size  of the entire population
    int cross =0;
    float crossover_value=0.8f;
    float mutation_value=0.5f;
    float crossover_rate, mutation_rate;

    int c=0, count=2;
    int [] fitness;
    long currentTimeMillis = System.currentTimeMillis(); // Get the current time in millisecond

    Random random = new Random(currentTimeMillis);  // Seed for random number generator

    try {
        //Get the clauses and variables from SAT.cnf file
        BufferedReader reader = new BufferedReader(new FileReader("SAT.cnf"));

        //Skip the first line that do not the variable
        reader.skip(33);

        //Red the file until the end of file
        String line;
        while((line= reader.readLine())!=null) {
            String[] tokens = line.split(" ");
            for (int k = 0; k < clause_size; k++) {
                if (!tokens[j].isEmpty() && !tokens[j].equals("0")) {
                    try {
                        formula[i][k] = Integer.parseInt(tokens[j]);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid integer: " + tokens[j]);
                    }
                }
                // Skipping to next integer
                reader.skip(2);
                // Reading next integer
                line = reader.readLine();
                if (line == null) break; // end of file
                tokens = line.split("\\s+"); // split by any whitespace
            }
            i++;
        }
        reader.close(); //close the reader

    } catch(IOException e){
        e.printStackTrace();

    }

    // Generate a population composed a 2D binary array where the row represent an individual and the columns represents the characteristics
    //of that indivudal (made of zeros and ones)
    for(int a=0; a<row; a++){
        for(int b=0; b<column; b++){
            population[a][b]= random.nextInt(2); //Number generate must be either 0 or 1
        }
    }

    fitness= new int [row];

    for(int a=0;a<row;a++){
        fitness[a]= fitness_function(population[a], formula);
    }
    int fit=fitness[0];
    int temp=0;
   
    for(int a=0;a<row;a++){
       System.out.print(" "+fitness[a]+ " ");
    } 
    for(int b=0; b<row;b++){
        if(fit>fitness[b]){fit=fitness[b]; temp=b;}
    }
    System.out.println(temp+" is the fittest individual "+ fit+ " is  its fitness");
    System.out.println("Displaying fittest individual ");
    System.out.println(" ");
     

    for(int b=0; c<column;c++){
        System.out.print(population[temp][b]+" ");
    }
    }
    

    //The fitness function
    public static int fitness_function(int data[], int formula[][]){
        int r,c,tmp,fitness_value=0;
        int b[];
        boolean clause;
        b= new int[clause_size];//initializing an array
        for(r=0;r<total_clause;r++){
            for(c=0;c<clause_size;c++){
                tmp=formula[r][c];
                if(tmp<0){
                    /*get the variable number from the formula and check if it's a negative value
                     * e.g. -9, then the value at the 9th position will be change with its complement
                    */
                    int varIndex = Math.abs(tmp) - 1; // Adjusting to 0-based index
                    switch(data[varIndex]){
                        case 0:
                        b[c]=1;
                        break;
                        default:
                        b[c]=0;
                        break;
                    }
                }
                else{
                    b[c]=data[tmp];
                }

            }
            boolean x=false,y=false,z=false;
            if (b[0]==0){x=false;} else if(b[0]==1){x=true;}
            if (b[1]==0){y=false;} else if(b[1]==1){y=true;}
            if (b[2]==0){z=false;} else if(b[2]==1){z=true;} 
            clause = x || y || z;
            if(clause  == false) {fitness_value++;}
        }
        return fitness_value;
    }

    //The crossover Function
    public static void crossover(int parent1[], int parent2[]){

        long currentTimeMillis = System.currentTimeMillis(); // Get the current time in millisecond
        Random random = new Random(currentTimeMillis);  // Seed for random number generator
        int crossover_point1, crossover_point2;
        crossover_point1= random.nextInt(101);
        

    }

}
