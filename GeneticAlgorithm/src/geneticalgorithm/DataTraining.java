/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author nimas
 */
public final class DataTraining {
   List <Double> pop = new ArrayList<>(); 
   List <Double> nextgenpop ;
   double []fitness;
   double a = 0.5;
   double b = 2.5;
   double fit=0;
   double glob_max[];
   int gen_max=100;
   int i_op;
   int n = 1;           //number of run
   int m =50;              //Population size
   int precision = 3;   //Population size
   double Pm;           // Mutation Probablility
   double Pc;           // Crossing Probability
   double Pr;           // Reproduction Probability
    
   void setPopulation( double range1, double range2){
        double fx;
        double tmp;
        for (int i = 0; i < m; i++) {
            fx= ThreadLocalRandom.current().nextDouble(range1, range2);
            tmp=round(fx);
            pop.add(tmp);
        }
        nextgenpop = new ArrayList<>(m); 
    }
   
    DataTraining(){
        setPopulation(a, b);
        m=pop.size();
        fitness = new double [50]; 
        glob_max = new double[2];
        double max_fit=0;
        for (int i = 0; i < gen_max; i++) {
            countFitness();
//            if(i%100==0) {
//                System.out.println(fit);
//            }
            if (fit>max_fit) {
                max_fit=fit;
                int s=0;
                for (int j = 0; j < m; j++) {
                    if (fitness[j]>fitness[s]) {
                    s=j;
                    }
                }
                glob_max[0]= pop.get(s);
                glob_max[1]= fitness[s];
                System.out.println("\n\nBest output: "+pop.get(s)+"  with fitness: "+fitness[s]+" at generation "+i);
            }
            
            
            i_op=0;
            while (i_op<m) {
                generaticOperation(); 
            }
            for (int j = 0; j < m; j++) {
                pop.set(j, nextgenpop.get(j));
            }
            nextgenpop.clear();
        }
        
        //result();
    }
    
    double round(double x){
        BigDecimal bd;
        double tmp;
            bd= new BigDecimal(Double.toString(x));
            bd= bd.setScale(precision, RoundingMode.HALF_UP);
            tmp=bd.doubleValue();
        
        return tmp;
    }
    
    void countFitness(){
        double pi = 3.14;
        double tmp;
        fit=0;
        for (int i = 0; i < m; i++) {
            //System.out.println(pop.get(i));
            tmp=Math.abs((Math.exp(pop.get(i)) * Math.sin(10*pi*pop.get(i)) +1)/pop.get(i)+5);
            
            fitness[i]= tmp;
            fit+=tmp;
        }
        //average fit
        fit=fit/m;
    }
    int rouleteSelection(){
        double[] P = new double[m];
        double[] Q = new double[m+1];
        double f=0;
        for (int i = 0; i < m; i++) {
            f+=fitness[i];
        }
        for (int i = 0; i < m; i++) {
            P[i]= (fitness[i]/f); 
        }
        //calculate distribution
        double tmp2=0;
        Q[0]=0;
        for (int i = 1; i <= m; i++) {
            tmp2+=P[i-1];
            Q[i]= tmp2;
        }
        //System.out.println("Q last"+ Q[m-1]);
        //remdom
        double r=0;
        r= ThreadLocalRandom.current().nextDouble(0, Q[m-1]);
        //checking
        int o=0;
        for (int i = 0; i < m; i++) {
            if (r>Q[i]&& r<=Q[i+1]) {
                o=i;
                break;
            }
        }
        
        return o;
    }
    
    
    
    void generaticOperation(){
        
        Pm = 0.2;
        Pc = 0.3;
        Pr = 1 - Pm - Pc;
        
        double choose;
                
        choose = Math.random();
        if (choose>=0&&choose<=Pm) {
            int sol=rouleteSelection();
            nextgenpop.add(mutationOp(pop.get(sol)));
            i_op++;
        }
        else if (choose>Pm && choose <= (Pm+Pc)){
            //cross op
            int sol1=rouleteSelection();
            int sol2=rouleteSelection();
            if (sol1==sol2) {
                sol2=rouleteSelection();
            }
            double[]d = crossOp(pop.get(sol1), pop.get(sol2));
            nextgenpop.add(d[0]);
            nextgenpop.add( d[1]);
            i_op+=2;            
        }
        else{
            //reproductio op
            int sol=rouleteSelection();
            nextgenpop.add(pop.get(sol));
        }
        
        
    }
    
    
    double mutationOp(double x){
        //change to binary
        Random ran = new Random();
        int d = (int) (((x-a)/(b-a))*Math.pow(10, precision));
        
        int []binary=convertBinary(d);
        
        int r = ran.nextInt(binary.length);
        if (binary[r]==0) binary[r]=1;
        else binary[r]=0;
        
        int j=0;
        for(int i=0;i<binary.length;i++){
            if(binary[i]== 1){
                j=(int) (j+ Math.pow(2,binary.length-1-i));
            }

        }
                 
             
        double ret = a+(j/Math.pow(10, precision))*(b-a);
        ret=round(ret);
        return ret;
    }
    
    double[] crossOp(double x, double y){
        Random ran = new Random();
        Integer d1 = (int) (((x-a)/(b-a))*Math.pow(10, precision));
        Integer d2 = (int) (((y-a)/(b-a))*Math.pow(10, precision));
        int []binary1=convertBinary(d1);
        int []binary2=convertBinary(d2);
        
        int r = ran.nextInt(binary1.length);
        
        int []tmp1 = binary1;
        for (int i = 0; i < binary1.length; i++) {
            if (i>r) {
                binary1[i]=binary2[i];
                binary2[i]=tmp1[i];
            }
        }
        int j=0;
        for(int i=0;i<binary1.length;i++){
            if(binary1[i]== 1){
                j=(int) (j+ Math.pow(2,binary1.length-1-i));
            }

        }int k=0;
        for(int i=0;i<binary2.length;i++){
            if(binary2[i]== 1){
                k=(int) (k+ Math.pow(2,binary2.length-1-i));
            }

        }
        double []ret= new double[2];
        ret[0]=a+(j/Math.pow(10, precision))*(b-a);
        ret[1]=a+(k/Math.pow(10, precision))*(b-a);
        ret[0]=round(ret[0]);
        ret[1]=round(ret[1]);
        
        return ret;
    }
    
    int[] convertBinary(int no) {
    int i = 0, temp[] = new int[10];
    int binary[];
    while (no > 0) {
        temp[i++] = no % 2;
        no /= 2;
    }
    binary = new int[10];
    int k = 0;
    for (int j = i - 1; j >= 0; j--) {
        binary[k++] = temp[j];
    }

    return binary;
}
    void result(){
        System.out.println("=====================================");
        System.out.println("             result ");
        System.out.println("=====================================");
        for (int i = 0; i < m; i++) {
            System.out.println(""+pop.get(i));
        }
    }
}
