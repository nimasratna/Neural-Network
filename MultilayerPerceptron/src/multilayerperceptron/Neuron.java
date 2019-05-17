/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilayerperceptron;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nimas
 */
public class Neuron {
    List <Double>input = new ArrayList<>();
    double outputz;
    double outputv;
    List <Double> weight = new ArrayList<>();
    double delta;
    double s;
    
    Neuron(){
        
    }
}
