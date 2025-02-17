package main.java.trevin;
//Author: Trevin van Hook
//Date: 2/16/2025
//Description: Compliant code for rule OBJ05_J

/**
 * class for node to perform this rule properly. 
 */
class Node{
    private int data;
    /*Constructor */
    public Node(int data){
        this.data = data;
    }
    /*toString method override */
    public String toString(){
        return data + " ";
    }
    public int getData(){
        return data;
    }
    
}

public class OBJ05_J {
    /*create a blank Node class array */
    private static Node[] nodeArray = new Node[10];
    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        /*set every node to a new node  */
        System.out.println("starting program...");
        for (int i = 0; i < 10;i++){
            /*get random number between 1 and 10 for example data */
            int randomNumber = (int)(Math.random() * 10) + 1;
            nodeArray[i] = new Node(randomNumber);
        }
        /*get a deep copy of the array */
        Node[] gottenNodeArray = getNodeArray();
        /*print out everything in the array */
        for (int i = 0; i < gottenNodeArray.length;i++){
            System.out.print(gottenNodeArray[i]);
        }
        System.out.print('\n');
        System.out.println("program complete...");
    }
    /**
     * returns a deep copy of the private node array
     * @return a deep copy of the private node array
     */
    public static Node[] getNodeArray(){
        Node[] tempArray = new Node[nodeArray.length];
        for (int i = 0; i < nodeArray.length;i++){
            tempArray[i] = new Node(nodeArray[i].getData());
        }
        return tempArray;
    }
}
