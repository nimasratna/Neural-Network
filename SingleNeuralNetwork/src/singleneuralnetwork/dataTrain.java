/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleneuralnetwork;

import static java.lang.Math.abs;

/**
 *
 * @author nimas
 */
public class dataTrain {
    
    double y=0;
    double delta=0;
    double out;
    int iteration_count=0;
    double accuracy=0.0001;
    double weight[];
    
    public dataTrain(double in[], double output) {
        input x1= new input(in);
        output o1= new output(output);
        weight w1= new weight();
        
        //count y
        for (int i = 0; i < in.length; i++) {
            y=y+(w1.w[i]*x1.x[i]);
        }
        
        //count delta
        delta=o1.z-y;
        
        double a= abs(delta);
        
        weight= new double[input.length];
        

        //data training
        do{
            y=0.0;
            weight=w1.set_new_weight(delta, x1.x); //setting nwe weight
            for (int i = 0; i < in.length; i++) {              
                y=y+w1.w[i];
            }
            delta=o1.z-y;
            a=abs(delta);
            iteration_count++;
            System.out.println( "delta "+iteration_count+": "+delta);
        }while((a>=accuracy));
        
        //display the weight when delta reach the accuracy
        System.out.println("");
        for (int i = 0; i < input.length; i++) {
            System.out.println("weight "+(i+1)+": "+weight[i]);
        }
        
        //dispay current y with new weight
        System.out.println("y= "+y);
        out=o1.z;
    
    }
    
    
    
        
}
