/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleneuralnetwork;

/**
 *
 * @author nimas
 */
public class SingleNeuralNetwork {

    /**
     * @param args the command line arguments
     */
    
    
    public SingleNeuralNetwork() {
        
    }

    public static void main(String[] args) {
        double[] input= new double[5];
        double[] input2= new double[5];
        input[0]=0.6;
        input[1]=1.7;
        input[2]=2.2;
        input[3]=1.9;
        input[4]=1.5;
        input2[0]=1.5;
        input2[1]=1.7;
        input2[2]=0.5;
        input2[3]=2.4;
        input2[4]=1.2;
        dataTrain dt = new dataTrain(input, 15.8);
        System.out.println("z in output: "+dt.out);
        
        
//        dataTrain dt2 = new dataTrain(input2, 45.9);        
//        System.out.println("z2 in output: "+dt2.out);
        
    }
    
}
