/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.mattgreen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lhebe
 */
public class MealFacts {
    private int min = 12000;
    private int max = 0;
    private int count = 0;
    private int totCal = 0;
    private String name;
    private final int ARRAY_SIZE = 50;
    private int[] calArray = new int[ARRAY_SIZE];
    
    public MealFacts(){
        
    }
    
    public MealFacts(String mealType){
        this.name = mealType;
    }
    public int getCal(){
        return calArray[count-1];
    }
    
    public int getArrayCal(){
        return calArray[count-1];
    }
    
    public int setAccum(int cal){
        calArray[count] = cal;
        count++;
        totCal += cal;
        if(cal < min)
            min = cal;
        else if(cal > max)
            max = cal;
        //space to do median?
        return count;
    }
    
    public int getMedian(){
        int median;
        int sInd, lowInd, i, lowVal;
        
        for(sInd = 0; sInd<(count-1); sInd++){
            lowInd = sInd;
            lowVal = calArray[sInd];
            for(i = sInd+1 ; i<count ; i++){
                if(calArray[i] < lowVal){
                    lowVal = calArray[i];
                    lowInd = i;
                }
            }
            System.out.println("median calArray[lowInd] " + calArray[lowInd]);
            calArray[lowInd] = calArray[sInd];
            calArray[sInd] = lowVal;
        }
        
        median = calArray[(count-1)/2];
        return median;
    }
    @Override
    public String toString() {
        /*return "name " + name + "\t"
                + "total " + totCal + "\t"
                + "count " + count + "\t"
                + "mean " + totCal/count + "\t"
                + "median " + getMedian() + "\t"
                + "min " + min + "\t"
                + "max " + max ;
        */
        return name + "\t"
                + totCal + "\t"
                + count + "\t"
                // + totCal/count + "\t"
                + min + "\t"
                + max + "\t"
                + getMedian();
                
    }

}
