// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 06: MET00-J. Validate method arguments

package main.java.derek;

public class MET00_J
{
    public static void main(String[] args) throws Exception
    {
        System.out.println(multiple(Integer.MAX_VALUE, 2));
    }
    public static int multiple(int first, int second)
    {
        long result = (long) first * second;
        // Noncompliant implementation:
        // return (int) result;

        // Compliant implementation:
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
        {
            System.out.println("MET00_J.multiple(): result is outside of the range of possible Integer values!");
            return Math.clamp(result, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        else
        {
            return (int) result;
        }
    }
}