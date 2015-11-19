
package assignment.pkg1;

import java.io.Serializable;

/**
 *
 * @author navidroohibroojeni
 */


public class Account implements Serializable{
    
    protected String name;
    protected double balance;
    
    
    public Account(){
        
    }

    public Account(double initBalance){
        balance = initBalance;
    }
    
    public Account(String accName, double initBalance){
        balance = initBalance;
        name = accName; 
    } 

    public String getName(){
        return name;
    }
    
    public double getBalance(){
        return balance;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setBalance(double balance){
        this.balance=balance;
    }
   
    
 
    
}



