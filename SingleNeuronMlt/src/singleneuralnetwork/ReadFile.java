/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleneuralnetwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author nimas
 */
public class ReadFile {
    private String path;
    public ReadFile(String file_path){
        path=file_path;
    }
    
    public double [][] OpenFile(int k, int n) throws IOException{

        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
        
        double [][]pt= new double[k][n];
        int i=0,j=0;
        while(i<k){
            j=0;
            while(j<n) {
                pt[i][j]=(double)scan.nextInt();
                j++;
            }
            i++;
        }
        return pt;
    }
    
}
