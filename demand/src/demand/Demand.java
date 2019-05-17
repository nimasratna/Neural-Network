/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demand;

import java.io.IOException;

/**
 *
 * @author nimas
 */
public class Demand {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String file_name="E:/MONOGRAPHIC/TEST.txt";
        String file_name2="E:/MONOGRAPHIC/Y.txt";
        
        ReadFile f1 = new ReadFile(file_name);
        ReadFile f2 = new ReadFile(file_name2);
        double[][] input=f1.OpenFile(8,6);
        double[] output=f2.OpenOutput(8);
        double learning_rate=0.00000005;
        dataTrain dt = new dataTrain(input, output, learning_rate);
        
    }
    
}
