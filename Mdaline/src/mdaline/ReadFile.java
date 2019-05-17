/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdaline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author nimas
 */
public class ReadFile {
    private String path;
    public ReadFile(String file_path){
        path=file_path;
    }
    
    public double[] OpenFile() throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        
        int numberOfLines = 4;
        String[] textData = new String[numberOfLines];

        String a="";
        char[] p= new char[4*4];
        double[]pt= new double[4*4];

        
            for (int i = 0; i < 4; i++) {
            textData[i] = textReader.readLine();
            a+=textData[i];
                System.out.println(""+textData[i]);
            }
            p=a.toCharArray();
            for (int l = 0; l < (4*4); l++) {
                    if(p[l]=='#'){
                        pt[l]=1;
                    }
                    else{
                        pt[l]=0;
                    }
                }
    return pt;
    }
    
}
