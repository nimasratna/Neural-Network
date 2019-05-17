/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdaline;

import java.io.IOException;

/**
 *
 * @author nimas
 */
public class FileData {
    public static void main(String[] args)throws IOException {
        String file_name1="D:/test/x.txt";
        String file_name2="D:/test/y.txt";
        String file_name3="D:/test/z.txt";
        
        ReadFile f1 = new ReadFile(file_name1);
        double [] pf1= f1.OpenFile();
        for (int i = 0; i < 16; i++) {
            System.out.print("  "+pf1[i]);
        }
    }
}
