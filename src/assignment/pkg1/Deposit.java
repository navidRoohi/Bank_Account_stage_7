
package assignment.pkg1;

import java.text.NumberFormat;

/**
 *
 * @author navidroohibroojeni
 */


public class Deposit extends Transaction{
    
    private double tAmtcash;
    private double tAmtCheck;
    private double totaldeposit;
 
    
    public Deposit(int tCount, int tId, double tAmtCheck,  double tAmtcash) {
        super(tCount, tId,tAmtCheck + tAmtcash );
        
       this.tAmtCheck = tAmtCheck;
        this.tAmtcash = tAmtcash;
        
        totaldeposit = tAmtCheck + tAmtcash;
    }
 
    public double getCash() {
        return tAmtcash;
    }
    
    public double getCheck(){
        return tAmtCheck;
    }
   

public double getTotalDeposit(){
        return totaldeposit;
    }
    

    @Override
    public String toStringDeposit(){
        
         return String.format("%2d\t%-10s\t    $%8.2f\t   $%8.2f  \t  $%8.2f", 
                 getTransNumber(),  getTransId(),   getCash(),getCheck(),getTotalDeposit() );
                            
    }
    
    
}


