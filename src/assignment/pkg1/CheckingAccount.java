package assignment.pkg1;

/**
 *
 * @author navid roohi broojeni
 */

import java.io.Serializable;
import java.util.ArrayList;

public class CheckingAccount extends Account implements Serializable{

    private double totalServiceCharge = 0.0;
    private int num = 0;
    String message;
    int transCount = 0;
    double transaction;
    public ArrayList<Transaction> transList = new ArrayList<>();
    
    public CheckingAccount(double balance) {
        super(balance);
    }

    public void setTotalServiceCharge(double currentServiceCharge) {

        Transaction tr = new Transaction(transCount, 3, currentServiceCharge);
        addTrans(tr);
        double addServiceCharge = 0;
        message = "";

        if (num < 1 && balance < 500) {
            // should happens just one time, so :
            message += "\nMessage: Your Balance is Below 500$ \nService charge below 500---Charge 5$ \n";
            addServiceCharge += 5;
            num++;
            tr = new Transaction(transCount, 3, 5.0);
            addTrans(tr);
        }
        if (balance < 50) {
            message += "\nMessage: Your Balance is Below 50$ \n";
        }
        if (balance < 0) {
            message += "\nWARNING! Your Balance is Negative \nService charge: below 0---Charge 10$ \n";
            addServiceCharge += 10;
            tr = new Transaction(transCount, 3, 10.0);
            addTrans(tr);
        }
        totalServiceCharge = totalServiceCharge + currentServiceCharge + addServiceCharge;
    }
    public void setBalance(int tranC, double transAmo) {
        if (tranC == 1) {
            balance = balance - transAmo;
        }
        if (tranC == 2) {
            balance = balance + transAmo;
        }
    }

    public double finalBalance() {
        return getBalance() - getTotalServiceCharge();
    }

    public void addTrans(Transaction t) {
        transList.add(t);
        transCount++;
    }    
  public double getTotalServiceCharge() {
        return totalServiceCharge;
    }
  
          
          
    public Transaction getTrans(int i) {
        return transList.get(i);
    }
    public int getTransCount() {
        return transCount;
    }

}
