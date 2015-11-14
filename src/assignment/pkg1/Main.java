package assignment.pkg1;

import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author navidroohibroojeni
 */
public class Main {

    // Global variables
    static String transaction;
    static double initBalance;
    static int transactionCode;
    static double currentServiceCharge;

    static String name;

    public static CheckingPanel panel;

    public static JFrame frame;

    public static void main(String[] args) {

        frame = new JFrame("Hello");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                
      int response  = JOptionPane.showConfirmDialog(null, "Do you want to Save ? ", "Confirm",
               JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
               
            if (response == JOptionPane.NO_OPTION) {
                          frame.dispose();
                    } else if (response == JOptionPane.YES_OPTION) {
                            
                        CheckingPanel obj =  new CheckingPanel();
                        obj.r
                       
                        
                 } else if (response == JOptionPane.CLOSED_OPTION) {
                             frame.dispose();
                 }
  
    
            }
        });

   
        
        panel = new CheckingPanel();
        frame.getContentPane().add(panel);
        frame.pack();
        // put the Jframe in the Center of the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static int getTransCode() {
        String tranC = JOptionPane.showInputDialog("Enter The Transaction Code "
                + "\n 1 for Check \n 2 for deposit \n 0 for exit");
        transactionCode = Integer.parseInt(tranC);

        return transactionCode;
    }

    public static double checkAmount() {
        String depA = JOptionPane.showInputDialog("Enter check amount: ");
        double checkAm = Double.parseDouble(depA);
        return checkAm;
    }

    public static String showDate() {
        Date date = new Date();
        return date.toString();
    }

    public static double getInitialBalance() {
        String initB = JOptionPane.showInputDialog("Enter The Initial Balance: ");
        initBalance = Double.parseDouble(initB);

        return initBalance;
    }

    public static String getBalanceName() {
        String input = JOptionPane.showInputDialog("Enter the Name: ");
        name = input;
        return name;
    }

}
