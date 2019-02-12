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
    private List<String> facts = new ArrayList<String>();
    
    public MealFacts(){
        
    }
    
    public MealFacts(String mealType){
        this.name = mealType;
    }
    
    public int setAccum(int cal){
        count++;
        totCal += cal;
        if(cal < min)
            min = cal;
        else if(cal > max)
            max = cal;
        //space to do median?
        return count;
    }
    @Override
    public String toString() {
        return "name " + name + "\t"
                + "total " + totCal + "\t"
                + "count " + count + "\t"
                + "mean " + totCal/count + "\t"
                + "min " + min + "\t"
                + "max " + max ;
                
    }

}
