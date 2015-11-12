/**
 *
 * @author navidroohibroojeni
 */
package assignment.pkg1;

import java.io.Serializable;

public class Transaction implements Serializable{

    // current value of the transCount
    private int transNumber;
    //  id ( 1=check  /  2=deposit   / 3 = service charge)
    private int transId;
    private double transAmt;

    public Transaction(int number, int id, double amount) {
        transNumber = number;
        transId = id;
        transAmt = amount;
    }

    public int getTransNumber() {
        return transNumber;
    }

    public String getTransId() {

        String type = "";

        if (transId == 1) {
            type = "Check";
        }
        if (transId == 2) {
            type = "Deposit";
        }
        if (transId == 3) {
            type = "Svc. Charg.";
        }
        return type;
    }

    public double getTransAmt() {
        return transAmt;
    }

    @Override
    public String toString() {
        return String.format("%2d\t%-10s\t\t$%8.2f", getTransNumber(), getTransId(), getTransAmt());
    }

    public String toStringDeposit() {
        return "";
    }

    public String toStringCheck() {
        return "";
    }

}
