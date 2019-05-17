/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilayerperceptron;

import java.io.IOException;

/**
 *
 * @author nimas
 */
public class MultilayerPerceptron {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String file_name3="D:/MultiLayerP/input.txt";
        
        ReadFile f1 = new ReadFile(file_name3);
        double[][] input=f1.OpenFile(4, 4);
        String file_name4="D:/MultiLayerP/output.txt";
        
        ReadFile f2 = new ReadFile(file_name4);
        double[][] output=f1.OpenFile(4, 4);
        TrainingSet ts= new TrainingSet(input, output);
        
        
    }
    
}
      