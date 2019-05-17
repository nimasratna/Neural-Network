/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structural;

import static java.lang.Math.abs;
import java.util.Random;

/**
 *
 * @author nimas
 */

public class SingleNeuron {
    int N=5;
    double z=17.5;
    double y=0.0;
    double learning_rate= 0.01;
    double d=0.0;
    int iteration_count=0;
    double accuracy=0.0001;
    double weight[] = new double[N];
    double input[]= new double[N];
    
    double set_new_weight(double x, double w){
        double v=w+(learning_rate*d*x);
        return v;
    }
    
    void train(){
        double a= abs(d);
        double t;
        do{
            y=0.0;
            for (int i = 0; i < N; i++) {
                t=set_new_weight(input[i], weight[i]);
                weight[i]=t;
                y=y+weight[i];
            }
            d=z-y;
            a=abs(d);
            iteration_count++;
            System.out.println( "delta "+iteration_count+": "+d);
        }while((a>accuracy));
    }
    
    
    public static void main(String[] args) {
        SingleNeuron sn = new SingleNeuron();
            
        sn.input[0]=0.6;
        sn.input[1]=1.7;
        sn.input[2]=2.2;
        sn.input[3]=1.9;
        sn.input[4]=1.5;
        Random rand=new Random();
        for (int i = 0; i < sn.N; i++) {
            sn.weight[i]=rand.nextGaussian(); //belum bisa range -1 sampai 1
            sn.y=sn.y+(sn.input[i]*sn.weight[i]);
            System.out.println("weight "+i+": "+sn.weight[i]);
        }
        sn.d=sn.z-sn.y;
              
        sn.train();
            
    }
}
