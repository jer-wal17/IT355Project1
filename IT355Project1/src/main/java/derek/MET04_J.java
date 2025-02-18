// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 06: MET04-J. Do not increase the accessibility of overridden or hidden methods

package main.java.derek;

public class MET04_J
{
    public static void main(String[] args)
    {
        Sub sub = new Sub();
        sub.isSkaDead();
    }

    private static class Super 
    {
        // Noncompliant implementation:
        // protected void isSkaDead() 
        // {
        //     System.out.println("No!");
        // }

        // Compliant implementation: 
        protected final void isSkaDead() 
        {
            System.out.println("No!");
        }
    }

    private static class Sub extends Super 
    {
        // Test code: will cause an error if compliant with rule
        // public void isSkaDead() 
        // {
        //     System.out.println("Yes!");
        // }
    }
}