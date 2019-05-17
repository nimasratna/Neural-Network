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
public class input {
    double x[];
    static int length;
    input(double n[]){
        x=new double[n.length];
        x=n;
        length=n.length;
    }
    int get_length(){
        return length;
    }
    
//    void set_input(int in, double g){
//        x[in]=g;
//    }
    double[] get_input(){
        return x;
    }
}
