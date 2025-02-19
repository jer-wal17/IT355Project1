// Written by Derek Reynolds
// For IT355 - Secure Software Development

// TODO: Rule 06: MET06-J. Do not invoke overridable methods in clone()
// TODO: Recommendation 06: MET52-J. Do not use the clone() method to copy untrusted method parameters

package main.java.derek;

public class MET06_J
{
    public static void main(String[] args)
    {
        
    }

    private static class Super implements Cloneable 
    {
        private Band[] dataArray;

        private Super(Band[] data) 
        {
            dataArray = data;
        }

        public Object clone() throws CloneNotSupportedException 
        {
            final Super clone = (Super) super.clone();
            clone.populateData();
            clone.dataArray = clone.deepCopy();
            return clone;
        }
        // Noncompliant implementation:

        // Compliant implementation: 
        private Band[] deepCopy() 
        {
            if (dataArray == null)
            {
                throw new NullPointerException();
            }
            Band[] dataArrayCopy = new Band[dataArray.length];
            for (int i = 0; i < dataArrayCopy.length; i++) 
            {
                dataArrayCopy[i] = (Band) dataArray[i].clone();
            }
            return dataArrayCopy;
        }

        private final void populateData() 
        {
            
        }
    }

    private static class Sub extends Super 
    {
        private Sub(char[] data) 
        {
            super(data);
        }
        
 
        public Object clone() throws CloneNotSupportedException {
            final Sub clone = (Sub) super.clone();
            clone.doSomething();
            return clone;
        }
        // Test code: will cause an error if compliant with rule
        // public void isSkaDead() 
        // {
        //     System.out.println(printStatement);
        // }
    }

    private static class Band extends Cloneable 
    {
        private String name;

        public Band(String bandName)
        {
            name = bandName;
        }

        public void setName(String bandName)
        {
            name = bandName;
        }

        @Override
        public Object clone() throws CloneNotSupportedException 
        {
            final Band clone = (Band) super.clone();
            clone.setName(this.name);
            return clone;
        }
    }
}