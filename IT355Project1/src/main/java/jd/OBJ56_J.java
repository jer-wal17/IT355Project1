// JD Waldron
// Rec. 05: OBJ56-J. Provide sensitive mutable classes with unmodifiable wrappers

package main.java.jd;

public class OBJ56_J
{
    private static Mutable mutable = new MutableProtector();

    public static void main(String[] args)
    {
        System.out.println(getMutable());
    }

    /**
     * Returns the mutable object
     * 
     * @return
     */
    public static Mutable getMutable()
    {
        return mutable;
    }
}

class Mutable
{
    private int[] array = new int[10];

    /**
     * Gets the array
     * 
     * @return the private array in Mutable
     */
    public int[] getArray()
    {
        return array;
    }

    /**
     * Changes the private array in Mutable
     * 
     * @param i
     */
    public void setArray(int[] i)
    {
        array = i;
    }
}

class MutableProtector extends Mutable
{
    @Override
    /**
     * Returns a cloned array to prevent mutability for read-only wrapper
     * 
     * @return the cloned array
     */
    public int[] getArray()
    {
        return super.getArray().clone();
    }

    @Override
    /**
     * Overrides the setArray to make it unsupported for the read-only wrapper
     */
    public void setArray(int[] i)
    {
        throw new UnsupportedOperationException();
    }
}
