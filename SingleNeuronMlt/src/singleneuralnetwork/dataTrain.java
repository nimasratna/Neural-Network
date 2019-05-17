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
    
    double y[];
    double delta;
    double out;
    int iteration_count=0;
    int epocks=10000;
    double accuracy=0.001;
    double weight[];
    
    
    public dataTrain(double in[][], double output[], double learning_rate) {
        input x1= new input(in);
        output o1= new output(output);
        weight w1= new weight();
        
        //display random weight
        for (int i = 0; i < input.length; i++) {
            System.out.println("random weight "+(i+1)+": "+w1.w[i]);
        }
        
        //count y[0]
       
        y= new double [input.k];
        for (int i = 0; i < input.k; i++) {
            for (int j = 0; j < input.length; j++) {
                y[i]+=(w1.w[j]*x1.x[i][j]);
            }
            delta+=(o1.z[i]-y[i]);
        }
        

        //double a= abs(delta);
       
        weight= new double[input.length];

            do{
                for (int i = 0; i < input.k; i++) {
                    y[i]=0;
                    delta=0;
                    for (int j = 0; j < input.length; j++) {
                        y[i]+=(w1.w[j]*x1.x[i][j]);
                    }
                    delta+=(o1.z[i]-y[i]);

                    weight=w1.set_new_weight((delta), x1.x[i], learning_rate);
                    System.out.println( "iteration "+iteration_count+": "+abs(delta));
                }

                iteration_count++;
               // System.out.println( "iteration "+iteration_count+": "+abs(delta));
                
            }while((iteration_count<epocks));
            //delta1=delta[j];
            //display new weight
            for (int i = 0; i < input.length; i++) {
                System.out.println("Weight: "+w1.w[i]);
            }
        
        
        
        
        System.out.println("");
                
//        //display new weight
//        for (int i = 0; i < input.length; i++) {
//            System.out.println("New Weight: "+w1.w[i]);
//        }
    
        
        //testing
        System.out.println("");
        System.out.println("---TESTING---");
        y[0]=0.0;

        for (int i = 0; i < input.length; i++) {              
            y[0]+=(w1.w[i]*x1.x[0][i]);
        }
        System.out.println("new weight * training set 1 = ");
        System.out.println("Y = "+ y[0]);
        System.out.println("z = "+ o1.z[0]);
        
        y[1]=0.0;
        for (int i = 0; i < input.length; i++) {              
                    y[1]=y[1]+(w1.w[i]*x1.x[1][i]);
                }
        System.out.println("new weight * training set 2");
        System.out.println("Y = "+y[1]);
        System.out.println("z = "+ o1.z[1]);
        
    }
    
    
    
        
}
