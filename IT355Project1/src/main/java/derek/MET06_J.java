// Written by Derek Reynolds
// For IT355 - Secure Software Development

// Rule 06: MET06-J. Do not invoke overridable methods in clone()
// Recommendation 06: MET52-J. Do not use the clone() method to copy untrusted method parameters

package main.java.derek;

public class MET06_J
{
    public static void main(String[] args)
    {
        Sub sub = new MET06_J().new Sub();
        cloneAndPrint(sub);
    }

    public static void cloneAndPrint(Sub toClone) 
    {
        try 
        {
            // Recommendation - noncompliant implementation:
            // Sub copy = (Sub)toClone.clone();

            // Recommendation - compliant implementation:
            Sub copy = new MET06_J().new Sub();
            copy.setData(toClone.deepCopy());
            System.out.println(copy.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private class Super implements Cloneable 
    {
        protected Band[] dataArray;

        private Super() 
        {
            populateData();
        }

        public Object clone() throws CloneNotSupportedException 
        {
            final Super clone = (Super) super.clone();
            clone.populateData();
            clone.dataArray = clone.deepCopy();
            return clone;
        }

        public String toString() 
        {
            String result = "";
            for (int i = 0; i < dataArray.length; i++) 
            {
                result += dataArray[i].name + "\n";
            }
            return result;
        }

        public void setData(Band[] data)
        {
            dataArray = data;
        }
        // Noncompliant implementation:
        // private Band[] deepCopy() throws CloneNotSupportedException 
        // {
        //     if (dataArray == null)
        //     {
        //         throw new NullPointerException();
        //     }
        //     Band[] dataArrayCopy = new Band[dataArray.length];
        //     for (int i = 0; i < dataArrayCopy.length; i++) 
        //     {
        //         dataArrayCopy[i] = (Band) dataArray[i].clone();
        //     }
        //     return dataArrayCopy;
        // }

        // protected void populateData() 
        // {
        //     dataArray = new Band[5];
        //     dataArray[0] = new Band("Reel Big Fish");
        //     dataArray[1] = new Band("Streetlight Manifesto");
        //     dataArray[2] = new Band("Catch 22");
        //     dataArray[3] = new Band("Less Than Jake");
        //     dataArray[4] = new Band("Goldfinger");
        // }

        // Compliant implementation: 
        public final Band[] deepCopy() throws CloneNotSupportedException 
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

        protected final void populateData() 
        {
            dataArray = new Band[5];
            dataArray[0] = new Band("Reel Big Fish");
            dataArray[1] = new Band("Streetlight Manifesto");
            dataArray[2] = new Band("Catch 22");
            dataArray[3] = new Band("Less Than Jake");
            dataArray[4] = new Band("Goldfinger");
        }
    }

    private class Sub extends Super 
    {
        private Sub() 
        {
            super();
        }
        
        public Object clone() throws CloneNotSupportedException {
            final Sub clone = (Sub) super.clone();
            clone.populateData();
            return clone;
        }

        // Test code: will cause an error if compliant with rule
        // protected void populateData() 
        // {
        //     dataArray = new Band[1];
        //     dataArray[0] = new Band("Kidz Bop");
        // }
    }

    private class Band implements Cloneable 
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

        public Object clone() throws CloneNotSupportedException 
        {
            final Band clone = new Band("");
            clone.setName(this.name);
            return clone;
        }
    }
}