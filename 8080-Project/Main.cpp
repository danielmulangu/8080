/*Project for Design and Analysis of Algorithms class CSCI8080 
 * Implementation of the genetic algorithm for trying to solve
 * the boolean satisifiability problem
 * Daniel Mulangu Kaseya
 * 72826452
 * dkaseya@unomaha.edu
 * daniel.mulangu@icloud.com
 * main.cpp 
 *  Created  on 3/10/24.
*/


#include<iostream>
#include<stdlib.h>
#include<time.h>
#define population_size 450
#define column 101
#define clauses 403
#define clause_size 3
using namespace std;
void crossover(int [column],int [column]);
void mutation(int [column]);
int fitnes_func(int [column],int [clauses][clause_size]);

  int main()
    {
        int formula[clauses][clause_size];
        int j,i=0;
        int population[population_size][column];
        int cross=0,k=0;
        float crossover_rate=0.8,mutation_rate=0.5;
        float crate,mrate;
        int iterate=0,count=2;
        int fitness[population_size];
        srand(time(NULL));
        FILE *f;
        f=fopen("SAT.cnf","r");
        fseek(f,33,0); //Move 33 steps due to the text at the beginning of the SAT file
            while(fscanf(f,"%d",&j)!=EOF)
            {
                // Store the formula in the file into 2-D array.
                for(int k=0;k<clause_size;k++)
                {
                        if(j!=0)
                    {
                    formula[i][k]=j;
                    }
                    fseek(f,2,1);
                    fscanf(f,"%d",&j);
                }
                i++;
            }

            for(int a=0;a<population_size;a++)
            {
                // Initialize the population 
                for(int b=0;b<column;b++)
                {
                    population[a][b]=rand()%2;
                }
            }

            for(int t=0;t<population_size;t++)
            {
                // Calculating the fitness function value
                fitness[t]=fitnes_func(population[t],formula);
            }

        int parents[population_size][column];
        int ka,test[4][column],ra;
        int fit[4],position=0,d=0;
        
        while(iterate<4500)
        {
                int min1=fitness[0],min2=fitness[0]; 
                int k=0,y=0;

            for(int f=1;f<population_size;f++)
            {
                if(min1>fitness[f])
                {
                min2=min1;
                y=k;//Obtain the position of best fitness no1
                min1=fitness[f];
                k=f;
                }
            }// Obtain the two individual with the best fitness

            fitness[0]=min1;
            fitness[1]=min2;
            // Store the individual with the two best finess as the two first individual
            // in the new generation
                for(int col=0;col<column;col++)
                {
                    parents[0][col]=population[k][col];
                    parents[1][col]=population[y][col];
                }
        
                while(count<population_size)
                {
                    for(ka=0;ka<4;ka++)
                    {
                        ra=rand()%100;
                            fit[ka]=fitness[ra];
                        while(d<101)
                        {
                            test[ka][d]=population[ra][d];
                            d++;
                        }d=0;
                    }
                    int min=fit[0];
                    for(int h=1;h<4;h++)
                    {
                        if(min>fit[h]) { min=fit[h];position=h;}
                    }
                    
                        for(int rw=0;rw<count;rw++)
                        {
                        for(int col=0;col<column;col++)
                        {parents[count][col]=test[position][col];}
                        }count++;
                }
                // Check if there is a need to perform the crossover according to the crossover rate
                for(int mlg=2;mlg<population_size-1;mlg++)
                {
                    float num=rand()%100;
                    crate=num/100;
                    if(crate<crossover_rate)
                        {
                        crossover(parents[mlg],parents[mlg+1]);
                        }
                    }

                // Check if there is a need to perform the mutation according to the crossover rate 
                for(int mlg=2;mlg<population_size;mlg++)
                {
                    float numb=rand()%100;
                    mrate=numb/100;
                    if(mrate<mutation_rate)
                    { mutation(parents[mlg]);}
                }
    
                    for(int rw=0;rw<population_size;rw++)
                    {
                        for(int col=0;col<column;col++)
                        {
                            population[rw][col]=parents[rw][col];
                        }
                    }
                    
                    for(int ft=2;ft<population_size;ft++)
                    {
                        fitness[ft]=fitnes_func(population[ft],formula);
                    }
                    
                    int fittest=fitness[0];
                    for(int f=1;f<population_size;f++)
                    {
                    if(fittest>fitness[f]) fittest=fitness[f];
                    }
                    //Display the fittest of every generation
                    cout<<"The fittest is "<<fittest<<endl;
                    iterate++;
                
                }
                
                    return 0;
                }

                int fitnes_func(int f[column],int data[clauses][clause_size])
                {
                    int r,c,tmp,fit_value=0;
                    bool b[clause_size],cl;
                    for(r=0;r<clauses;r++)
                    {
                        for(c=0;c<clause_size;c++)
                        {
                            tmp=data[r][c];
                            if(tmp<0)
                            {
                            switch(f[tmp])
                            {
                                case 0:
                                b[c]=1;
                                break;
                                default:
                                b[c]=0;
                                break;
                            }
                        }
                            else
                            {
                            b[c]=f[tmp];
                            }
                        }
                        cl=b[0]||b[1]||b[2];
                        if(cl==0){ fit_value++;}
                    }
                    return (fit_value);
                }

                void crossover(int p1[column],int p2[column])
                {
                    int first_cross_point,sec_cross_point;
                    int tmp;
                    first_cross_point=rand()%101;
                    sec_cross_point=rand()%101;
                    if(first_cross_point>sec_cross_point){
                        //Perform sawp if 1st point is greater than 2nd
                         int temp=first_cross_point; 
                         first_cross_point=sec_cross_point; 
                         sec_cross_point=temp;}
                    for(int cr=first_cross_point;cr<=sec_cross_point;cr++)
                        {
                            //Crossover operation
                            tmp=p1[cr];
                            p1[cr]=p2[cr];
                            p2[cr]=tmp;
                        }
                }

                void mutation(int pr[column])
                {
                    // Perform the crossover using 
                    // bit flipping
                    int spoint,fpoint;
                    spoint=rand()%101;
                    fpoint=rand()%101;
                    if(pr[spoint]==0) pr[spoint]=1;
                    else pr[spoint]=0;
                    if(pr[fpoint]==0) pr[fpoint]=1;
                    else pr[fpoint]=0;
                    
                }
