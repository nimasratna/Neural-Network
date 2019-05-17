/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleneuralnetwork;

import java.util.Random;

/**
 *
 * @author nimas
 */
public class weight {
    
    double w[];
    weight(){
        w=new double[input.length];
        Random rand=new Random();
        for (int i = 0; i < input.length; i++) {
            w[i]=rand.nextGaussian(); //range -1 to 1
        }
        
    }
    double[] set_new_weight(double d, double input[], double learning_rate){
        double tmp=0.0;
        for (int i = 0; i < w.length; i++) {
//            for (int j = 0; j < d.length; j++) {
//                tmp=tmp+(d[j]*input[i]);    
//            }
            double v=w[i]+(learning_rate*d*input[i]);
            w[i]=v;
            
        }
        
        return w;
    }
            
    double[] get_weight(){
//        for (int i = 0; i < w.length; i++) {
//            System.out.println("weight "+i+": "+w[i]);
//        }
        return w;
    }
    
}
