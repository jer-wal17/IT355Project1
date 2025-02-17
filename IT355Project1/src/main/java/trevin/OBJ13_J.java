package main.java.trevin;
//Author: Trevin van Hook
//Date: 2/16/2025
//Description: Compliant code example for rule OBJ13_J and recommendation OBJ52_J

import java.util.Arrays;

public class OBJ13_J {
    /**Integer array with numbers 1 to 5 in it */
    private static final Integer[] numberList = new Integer[]{1,2,3,4,5};
    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("starting program");
        System.out.println("Number List: " + Arrays.toString(getNumberList()));
        System.out.println("getting index 3: " + numberList[3]);
        System.out.println("ending program");
    }
    /**
     * method to get a number from the numberList array given a index
     * @param position index to grab the number from the array
     * @return the number from the index position in the numberList Array
     */
    public static Integer getNumber(int position){
        if (numberList.length >= position){
            return numberList[position];
        }
        return null;
    }
    /**
     * A method to return the object list
     * @return a cloned object of the numberList
     */
    public static Integer[] getNumberList(){
        return numberList.clone();
    }
}
