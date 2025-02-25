// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 06: MET05-J. Ensure that constructors do not call overridable methods
// TODO: Recommendation 06: MET50-J. Avoid ambiguous or confusing uses of overloading

package main.java.derek;

public class MET05_J
{
    public static void main(String[] args)
    {
        Sub sub = Sub.createSub();
    }

    private static class Super 
    {
        public static Super createSuper() 
        {
            Super result = new Super();
            result.isSkaDead();
            return result;
        }

        // Noncompliant implementation:
        // public void isSkaDead() 
        // {
        //     System.out.println("No!");
        // }

        // Compliant implementation: 
        public final void isSkaDead() 
        {
            System.out.println("No!");
        }
    }

    private static class Sub extends Super 
    {
        private String printStatement = "Yes!";
        public static Sub createSub()
        {
            super();
            Sub result = Super.createSuper();
            return result;
        }
        // Test code: will cause an error if compliant with rule
        // public void isSkaDead() 
        // {
        //     System.out.println(printStatement);
        // }
    }
}