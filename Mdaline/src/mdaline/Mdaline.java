/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdaline;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author nimas
 */
public class Mdaline {

    /**
     * @param args the command line arguments
     */
    int templatePattern;
    int size;
    double[][] inputNeuron;
    double[][] weightNeuron;
    double[] yNeuron;
    double[] weight;
    double[] conf;
    
    void weight(double[][] pat){
        weightNeuron= new double[templatePattern][size];
        weightNeuron=pat;
    }
    
    
    double[][] Training(double[][] pattern){
        double t;
        for (int i = 0; i < templatePattern; i++) {
            t=0.0;
            for (int j = 0; j < size; j++) {
                t+=pattern[i][j];
            }
            for (int j = 0; j < size; j++) {
                pattern[i][j]/=Math.sqrt(t);
            }
        }     
        return pattern;
    }

    
    void operation(double[][]pattern){
        weight(Training(pattern));
        
    }
    
    
    int testing(double[] testpattern){
        double tmp=0.0;
        for (int i = 0; i < size; i++) {
            tmp+=testpattern[i];
        }
        for (int i = 0; i < size; i++) {
            testpattern[i]/=Math.sqrt(tmp);
        }
        double ytest[]= new double[templatePattern];
        conf= new double[templatePattern];
        
        for (int i = 0; i < templatePattern; i++) {
            ytest[i]=0.0;
            //conf[i]=0.0;
            for (int j = 0; j < size; j++) {
                ytest[i]+=weightNeuron[i][j]*testpattern[j];
            }
            conf[i]=ytest[i];//Math.sqrt(Math.pow(Math.min(ytest[i],yNeuron[i]),2)/Math.pow(Math.max(ytest[i],yNeuron[i]),2));
            System.out.println("neuron "+i+" responded with the value "+conf[i]);
        }

        int out=0;
        for (int i = 1; i < templatePattern; i++) {
            if (Math.abs(conf[out])<(Math.abs(conf[i]))){
                out=i;
            }
        }
        return out;
        
    }
    
    
    public static void main(String[] args) throws IOException {
        int n=4;
        int k=3;
        double [][]pt = new double[k][n*n];
        String file_name1="D:/test/x.txt";
        String file_name2="D:/test/y.txt";
        String file_name3="D:/test/z.txt";
        
        ReadFile f1 = new ReadFile(file_name1);
        ReadFile f2 = new ReadFile(file_name2);
        ReadFile f3 = new ReadFile(file_name3);
        pt[0]= f1.OpenFile();
        pt[1]= f2.OpenFile();
        pt[2]= f3.OpenFile();
                
        Mdaline m= new Mdaline();
        m.templatePattern=k;
        m.size=n*n;
        m.operation(pt);
        
        //////////////////////////testing///////////////////////
        
        String file_name_testing="D:/test/testing.txt";
        ReadFile ftest = new ReadFile(file_name_testing);
        double []ptest= ftest.OpenFile();
                
        int ab=m.testing(ptest);
        System.out.println("=>  network has recognized network "+ab+" with the value = "+(m.conf[ab]));
    }
}
