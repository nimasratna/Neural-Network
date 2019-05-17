/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demand;

/**
 *
 * @author nimas
 */
public class input {
    double x[][];
    static int length;
    static int k;
    input(double n[][]){
        x=new double[n.length][n[0].length];
        x=n;
        k=n.length;
        length=n[0].length;
    }
    
}
