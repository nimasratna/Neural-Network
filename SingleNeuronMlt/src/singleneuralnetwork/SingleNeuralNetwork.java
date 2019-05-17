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
                     
        double[][] input = { {3.5, 0.4, 1.2, 3.6, 2.5},
                             {1.8, 2.1, 1.5, 5.4, 1.4},
                             {0.7, 1.2, 1.2, 1.4, 0.9},
                             {1.4, 0.7, 0.9, 2.3, 1.2}
                             };

        double[] output={52.0, 59.9, 25.2, 30.6};
        
        double[][] input3 = { {3.43776, 4.51051},
                             {-7.40160, 4.66901}
                             };

        double[] output3={0.71427, -0.12471};
        
        double learning_rate=0.05;
        //double learning_rate2=0.04;
        dataTrain dt = new dataTrain(input, output, learning_rate);
//        double ytest=0.0;
//        double [] test={3,1};
//        for (int i = 0; i < 2; i++) {
//            ytest+=dt.weight[i]*test[i];
//        }
//        System.out.println("Y Test = "+ytest);
        
        
//        dataTrain dt2 = new dataTrain(input2, 45.9);        
//        System.out.println("z2 in output: "+dt2.out);
        
    }
    
}
