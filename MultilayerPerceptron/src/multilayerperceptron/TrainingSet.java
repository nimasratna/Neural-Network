/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multilayerperceptron;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.text.Position;

/**
 *
 * @author nimas
 */
public class TrainingSet {
    
    int epoks=1000000;
    double trainingStep=0.07;
    int trainingPattern;
    int n;
    int kLayer=3;
    List <List<Neuron>> Layer= new ArrayList<>();
    double sigmoidal_function;
    Neuron bias = new Neuron();
    double tmp;
    double sumerror;
    
    void setNetwork(int a){
        for (int i = 0; i < 3; i++) {
            Layer.add(new ArrayList<Neuron>());
        }
        
        for (int i = 0; i < a; i++) {
            Neuron l1 = new Neuron();
            l1.weight.add(1.0);
            Layer.get(0).add(l1);
        }
        
        bias.outputv = 1.0;
        
        for (int i = 0; i < 2; i++) {
            Neuron l2 = new Neuron();
            Layer.get(1).add(l2);
        }
        
        for (int i = 0; i < a; i++) {
            Neuron l3 = new Neuron();
            Layer.get(2).add(l3);
        }
    }
    
    TrainingSet(double [][]datain, double [][]dataout){
         
        trainingPattern=datain.length;
        n = datain[0].length;
        setNetwork(n);
        training(datain, dataout);
    }
    
    void setRandomWeight(){
        Random rand=new Random();
        int ws;
        for (int i = 1; i <= 2; i++) {
            for (int j = 0; j < (Layer.get(i)).size(); j++) {
                ws=(Layer.get(i-1)).size()+1;
                for (int k = 0; k < ws; k++) {
                    ((Layer.get(i)).get(j)).weight.add(rand.nextGaussian()) ;
                }
            }
        }
    }
        
    void training(double [][]in, double [][]out){
        int e=0;
        setRandomWeight();

        //copying layer
        for (int j = 0; j < in[0].length; j++) {
            ((Layer.get(0)).get(j)).input.add(in[0][j]);
            ((Layer.get(0)).get(j)).outputv=((((Layer.get(0)).get(j)).input).get(0))*(((Layer.get(0)).get(j)).weight).get(0);
            //System.out.println("out L 1: "+((Layer.get(0)).get(j)).outputv);
        }
        //linked to the next layer
            for (int k = 0; k < (Layer.get(1)).size(); k++) {
                for (int i = 0; i < Layer.get(0).size(); i++) {
                    (Layer.get(1)).get(k).input.add(((Layer.get(0)).get(i)).outputv);
                    
                }
                //addbias
                (Layer.get(1)).get(k).input.add(bias.outputv);
                //System.out.println("bias out: "+bias.outputv);
            }
           
            //System.out.println("Layer 1 size: "+Layer.get(1).get(0).input.size());

        //hidden layer
        tmp=0;
        for (int j = 0; j < Layer.get(1).size(); j++) {
            for (int k = 0; k < Layer.get(1).get(j).input.size(); k++) {
                tmp+=Layer.get(1).get(j).input.get(k)*Layer.get(1).get(j).weight.get(k);
            }
            Layer.get(1).get(j).s=tmp;
            Layer.get(1).get(j).outputv=sigmoidal_funct(Layer.get(1).get(j).s);
            
        }
        //linked to next layer
        for (int k = 0; k < Layer.get(2).size(); k++) {
            for (int i = 0; i < Layer.get(1).size(); i++) {
                Layer.get(2).get(k).input.add(Layer.get(1).get(i).outputv);   
            }
            //addbias
            (Layer.get(2)).get(k).input.add(bias.outputv);
        }
        
        //output layer
        tmp=0;
        for (int j = 0; j < Layer.get(2).size(); j++) {
            for (int k = 0; k < Layer.get(2).get(j).input.size(); k++) {
                tmp+= Layer.get(2).get(j).input.get(k) * Layer.get(2).get(j).weight.get(k);
            }
            Layer.get(2).get(j).s=tmp;
            Layer.get(2).get(j).outputz=sigmoidal_funct(Layer.get(2).get(j).s);
            Layer.get(2).get(j).outputv=out[0][j];
            
        }

        // back propagation
        do{
            //System.out.println("iteration : "+e); 
            sumerror=0.0;
            for (int i = 0; i < trainingPattern; i++) {
                //System.out.println("iteration : "+e+" training : "+i+" ");
                
                //copying layer
            for (int j = 0; j < Layer.get(0).size(); j++) {
                ((Layer.get(0)).get(j)).input.set(0 ,in[i][j]);
                ((Layer.get(0)).get(j)).outputv=((((Layer.get(0)).get(j)).input).get(0))*(((Layer.get(0)).get(j)).weight).get(0);    
            }
            //linked to the next layer
            for (int k = 0; k < (Layer.get(1)).size(); k++) {
                for (int j = 0; j < Layer.get(0).size(); j++) {
                    (Layer.get(1)).get(k).input.set(j ,((Layer.get(0)).get(j)).outputv);
                    
                }
            }
            
            //hidden layer
            tmp=0;
            for (int j = 0; j < Layer.get(1).size(); j++) {
                for (int k = 0; k < Layer.get(1).get(j).input.size(); k++) {
                    tmp+=Layer.get(1).get(j).input.get(k)*Layer.get(1).get(j).weight.get(k);
                }
                Layer.get(1).get(j).s=tmp;
                Layer.get(1).get(j).outputv=sigmoidal_funct(Layer.get(1).get(j).s);
                
                
            }
            //linked to next layer
            for (int k = 0; k < Layer.get(2).size(); k++) {
                for (int j = 0; j < Layer.get(1).size(); j++) {
                    Layer.get(2).get(k).input.set(j ,Layer.get(1).get(j).outputv);                
                }
            }
            

            //output layer
            tmp=0;
            for (int j = 0; j < Layer.get(2).size(); j++) {
                for (int k = 0; k < Layer.get(2).get(j).input.size(); k++) {
                    tmp+= Layer.get(2).get(j).input.get(k) * Layer.get(2).get(j).weight.get(k);
                }
                Layer.get(2).get(j).s=tmp;
                Layer.get(2).get(j).outputv=sigmoidal_funct(Layer.get(2).get(j).s);
                Layer.get(2).get(j).outputz=out[i][j];
            }                
                
                
                for (int j = 2; j >= 1; j--) {
                    for (int k = 0; k < Layer.get(j).size(); k++) {
                        //calculate delta
                        Layer.get(j).get(k).delta= det_sigmoidal_function(Layer.get(j).get(k).s)*(Layer.get(j).get(k).outputz-Layer.get(j).get(k).outputv);
                        sumerror+=Math.pow(Layer.get(j).get(k).delta, 2);
                    }
                    for (int k = 0; k < Layer.get(j-1).size(); k++) {
                        //calculate z perv layer
                        tmp=0;
                        for (int l = 0; l < Layer.get(j).size(); l++) {
                            tmp+=Layer.get(j).get(l).delta * Layer.get(j).get(l).weight.get(k);
                        }
                        Layer.get(j-1).get(k).outputz=tmp;
                    }
                }
                 for (int j = 2; j >= 1; j--) {
                    for (int k = 0; k < Layer.get(j).size(); k++) {
                    // change weight
                        for (int l = 0; l < Layer.get(j).get(k).weight.size(); l++) {
                            Layer.get(j).get(k).weight.set(l, Layer.get(j).get(k).weight.get(l)+(trainingStep*Layer.get(j).get(k).input.get(l)*Layer.get(j).get(k).delta)) ;
                        }
                    }
                 }
                    
                
                
            
            }
            if(e % 200 == 0) System.out.println(sumerror);
            e++;
        }while (e<epoks);
        
        System.out.println("================result=================");
        System.out.println("");
//        System.out.println("Weights:");
//        for (int i = 0; i < Layer.size(); i++) {
//            System.out.println("Layer "+i);
//            for (int j = 0; j < Layer.get(i).size(); j++) {
//                System.out.println("Neuron "+j);
//                for (int k = 0; k < Layer.get(i).get(j).weight.size(); k++) {
//                    System.out.println(Layer.get(i).get(j).weight.get(k));
//                }
//            }
//        }
        
        for (int i = 0; i < trainingPattern; i++) {
            System.out.println("Training Pattern "+i);
            for (int j = 0; j < Layer.get(0).size(); j++) {
                ((Layer.get(0)).get(j)).input.set(0 ,in[i][j]);
                ((Layer.get(0)).get(j)).outputv=((((Layer.get(0)).get(j)).input).get(0))*(((Layer.get(0)).get(j)).weight).get(0);    
            }
            //linked to the next layer
            for (int k = 0; k < (Layer.get(1)).size(); k++) {
                for (int j = 0; j < Layer.get(0).size(); j++) {
                    (Layer.get(1)).get(k).input.set(j ,((Layer.get(0)).get(j)).outputv);
                    
                }
            }
            
            //hidden layer
            tmp=0;
            System.out.println("\nOutput Hidden Layer: ");
            for (int j = 0; j < Layer.get(1).size(); j++) {
                for (int k = 0; k < Layer.get(1).get(j).input.size(); k++) {
                    tmp+=Layer.get(1).get(j).input.get(k)*Layer.get(1).get(j).weight.get(k);
                }
                Layer.get(1).get(j).s=tmp;
                Layer.get(1).get(j).outputv=sigmoidal_funct(Layer.get(1).get(j).s);
                System.out.println(Layer.get(1).get(j).outputv);
                
                
            }
            //linked to next layer
            for (int k = 0; k < Layer.get(2).size(); k++) {
                for (int j = 0; j < Layer.get(1).size(); j++) {
                    Layer.get(2).get(k).input.set(j ,Layer.get(1).get(j).outputv);                
                }
            }
            

            //output layer
            tmp=0;
            System.out.println("\nOutput Layer 2: ");
            for (int j = 0; j < Layer.get(2).size(); j++) {
                for (int k = 0; k < Layer.get(2).get(j).input.size(); k++) {
                    tmp+= Layer.get(2).get(j).input.get(k) * Layer.get(2).get(j).weight.get(k);
                }
                Layer.get(2).get(j).s=tmp;
                Layer.get(2).get(j).outputv=sigmoidal_funct(Layer.get(2).get(j).s);
                System.out.println(Layer.get(2).get(j).outputv);
            }
        }
        
       
    }
    
    
    double det_sigmoidal_function(double s){
        double tm=1/(1+Math.pow(10, s*(-1)));
        sigmoidal_function= tm* (1-tm);
        return sigmoidal_function;
    }
    double sigmoidal_funct(double s){
        double tm=1/(1+Math.pow(10, s*(-1)));
        return tm;
    }     
}
