package main.java;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.SerializablePermission;
import java.text.DecimalFormat;

abstract class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private long uniqueId;
    private long bankId;
    private transient long ownerId;
    protected transient double balance;

    /**
     * Constructor class
     * 
     * @param bankID  bank account id
     * @param ownerID owner account id
     * @param balance starting balance for the account
     */
    public BankAccount(long uniqueId, long bankId, long ownerId, double balance) {
        this.uniqueId = uniqueId;
        this.bankId = bankId;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    /**
     * Copy constructor for a bank account object
     * 
     * @param account passed in bank account object
     */
    public BankAccount(BankAccount account) {
        this.uniqueId = account.getUniqueId();
        this.bankId = account.getBankId();
        this.ownerId = account.getOwnerId();
        this.balance = account.getBalance();
    }

    @Override
    public String toString() {
        String outString = "Unique ID: " + uniqueId + "\n";
        outString += "Bank ID: " + bankId + "\n";
        outString += "Owner ID: " + ownerId + "\n";
        DecimalFormat df = new DecimalFormat("0.00");
        outString += "Balance: $" + df.format(balance) + "\n";

        return outString;
    }

    public String balanceAsString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "$" + df.format(this.getBalance());
    }

    /* Getters and setters */
    public final long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public final long getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public final long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public final double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract double deposit(double amount);

    public abstract double withdraw(double amount);

    /**
     * Custom serialization method.
     * This method ensures that sensitive data is not serialized in plaintext
     * and checks for the "serialize" permission using the SecurityManager.
     *
     * @param out The ObjectOutputStream to write the object to.
     * @throws IOException If an I/O error occurs during serialization.
     */
    @SuppressWarnings("removal")
    protected final void writeObject(ObjectOutputStream out) throws IOException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("serialize"));
        }
        out.defaultWriteObject();
        out.writeLong(ownerId);
        out.writeDouble(balance);
    }

    /**
     * Custom deserialization method.
     * This method ensures that sensitive data is securely handled during deserialization
     * and checks for the "deserialize" permission using the SecurityManager.
     *
     * @param in The ObjectInputStream to read the object from.
     * @throws IOException            If an I/O error occurs during deserialization.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     * @throws InvalidObjectException  If invalid data is detected during deserialization.
     */
    @SuppressWarnings("removal")
    protected final void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("deserialize"));
        }
        in.defaultReadObject();
        ownerId = in.readLong();
        balance = in.readDouble(); 
        if (this.balance < 0) {
            throw new InvalidObjectException("Invalid balance detected during deserialization");
        }
    }
}
