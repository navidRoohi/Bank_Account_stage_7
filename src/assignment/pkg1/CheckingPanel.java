package assignment.pkg1;

/**
 *
 * @author navidroohibroojeni
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;

public class CheckingPanel extends JPanel implements Serializable {
    
    // declear the file location that needs to write or read from 
    public static String filename = "/Users/navidroohibroojeni/Desktop/JAVA-DB-CLASS/lplp.dat";


    double totalDeposit;

    JFrame frameForDeposit;
    JTextField cashTextField;
    JTextField checkTextField;
    JButton okBtn;
    JButton cancelBtn;

    double cash;
    double check;

    JRadioButton radio1;
    JRadioButton radio2;
    JRadioButton radio3;
    JRadioButton radio4;
    JRadioButton radio5; // radio 5 and 6 are for reading/wrting from/to file. 
    JRadioButton radio6;

    JLabel lable;

    double amount = 0;
    String transaction;
    double currentServiceCharge;
    int transactionCode;

    static String name = Main.getBalanceName();
    static double initBalance = Main.getInitialBalance();

    public static CheckingAccount checkingAccountObj;

    
    JTextArea text = new JTextArea();

    String message = "";

    DecimalFormat form;

    /*
     Constractor
     */
    public CheckingPanel() {
        
        checkingAccountObj = new CheckingAccount(initBalance);
        public static boolean ifSaved = false;
 public static void chooseFile(int ioOption);

        form = new DecimalFormat("$##,##0.00;($##,##0.00)");

        text.setFont(new Font("Monospaced", Font.PLAIN, 14));

        radio1 = new JRadioButton("Enter Transaction");
        radio2 = new JRadioButton("List All Transactions");
        radio3 = new JRadioButton("List All Checks ");
        radio4 = new JRadioButton("List All Deposits");
        radio5 =  new JRadioButton("Read From File");
        radio6 = new JRadioButton("Write To The File");

        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
        group.add(radio4);
        group.add(radio5);
        group.add(radio6);

        EOptionListener1 listener = new EOptionListener1();
        radio1.addActionListener(listener);
        radio2.addActionListener(listener);
        radio3.addActionListener(listener);
        radio4.addActionListener(listener);
        radio5.addActionListener(listener);
        radio6.addActionListener(listener);

        lable = new JLabel("Checking Account Actions");
        lable.setFont(new Font("Helvetica", Font.BOLD, 24));

        add(lable);

        add(radio1);
        add(radio2);
        add(radio3);
        add(radio4);
        add(radio5);
        add(radio6);
        
        // create Grid Layout with 7 row and 1 column
        setLayout(new GridLayout(7,1));
        
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(350, 200));

    }

    // deposit panel
    public void panelDeposit() {

        int WINDOW_WIDTH = 220;
        int WINDOW_HEIGHT = 180;

        frameForDeposit = new JFrame("Deposit");
        frameForDeposit.setLocationRelativeTo(null);

        frameForDeposit.setTitle("Deposit");
        frameForDeposit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameForDeposit.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        JLabel messageCash = new JLabel("Cash");
        JLabel messageCheck = new JLabel("Check");

        cashTextField = new JTextField(15);
        checkTextField = new JTextField(15);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");

        JPanel panelB = new JPanel();

        panelB.add(messageCash);
        panelB.add(cashTextField);
        panelB.add(messageCheck);
        panelB.add(checkTextField);
        panelB.add(okBtn);
        panelB.add(cancelBtn);

        EOptionListener2 listener2 = new EOptionListener2();
        okBtn.addActionListener(listener2);
        cancelBtn.addActionListener(listener2);

        frameForDeposit.add(panelB);

        frameForDeposit.setVisible(true);

    }

    // listener for deposit panel
    private class EOptionListener2 implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            Object source = event.getSource();
            if (source == okBtn) {

                String input1, input2;

                input1 = cashTextField.getText();
                cash = Double.parseDouble(input1);

                input2 = checkTextField.getText();
                check = Double.parseDouble(input2);

                totalDeposit = cash + check;

                Deposit newDeposit = new Deposit(checkingAccountObj.transCount, 2, check, cash);

                checkingAccountObj.addTrans(newDeposit);

                frameForDeposit.dispose();

                checkingAccountObj.setBalance(transactionCode, totalDeposit);
                checkingAccountObj.setTotalServiceCharge(currentServiceCharge);

                JOptionPane.showMessageDialog(null, Main.name + " account.\n"
                        + "Transaction: " + transaction + " in amount of " + form.format(totalDeposit) + "\n"
                        + "Current Balance: " + form.format(checkingAccountObj.getBalance()) + "\n"
                        + "Service charge: " + transaction + " Charge--- " + form.format(currentServiceCharge) + "\n" + checkingAccountObj.message
                        + "\nTotal Service Charge: " + form.format(checkingAccountObj.getTotalServiceCharge()) + "\n"
                        + "\n Date: " + Main.showDate());

            }
            if (source == cancelBtn) {

                frameForDeposit.dispose();

            }

        }
    }

    private class EOptionListener1 implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            Object source = event.getSource();

            // check the radio from here:
            if (source == radio1) {
                // get tansaction code
                transactionCode = Main.getTransCode();

                if (transactionCode != 0) {

                    if (transactionCode == 1) {

                        String input = JOptionPane.showInputDialog("Enter the check number:");
                        int checkNumber = Integer.parseInt(input);

                        amount = Main.checkAmount();
                        transaction = "Check";
                        currentServiceCharge = 0.15;
                        Check tr = new Check(checkingAccountObj.transCount, 1, amount, checkNumber);
                        checkingAccountObj.addTrans(tr);

                        checkingAccountObj.setBalance(transactionCode, amount);
                        checkingAccountObj.setTotalServiceCharge(currentServiceCharge);

                        JOptionPane.showMessageDialog(null, Main.name + " account.\n"
                                + "Transaction: " + transaction + "#" + checkNumber + " in amount of " + form.format(amount) + "\n"
                                + "Current Balance: " + form.format(checkingAccountObj.getBalance()) + "\n"
                                + "Service charge: " + transaction + " Charge--- " + form.format(currentServiceCharge) + "\n" + checkingAccountObj.message
                                + "\nTotal Service Charge: " + form.format(checkingAccountObj.getTotalServiceCharge()) + "\n"
                                + "\n Date: " + Main.showDate());
                    }

                    if (transactionCode == 2) {

                        panelDeposit();
                        amount = totalDeposit;

                        transaction = "Deposit";
                        currentServiceCharge = 0.10;
                    }

                } else {
                    transaction = "End";
                    JOptionPane.showMessageDialog(null, " Transaction: " + transaction
                            + "\n Current Balance: " + form.format(checkingAccountObj.getBalance())
                            + "\n Total Service Charge: " + form.format(checkingAccountObj.getTotalServiceCharge())
                            + "\n Final Balance: " + form.format(checkingAccountObj.finalBalance())
                            + "\n\n Date: " + Main.showDate());
                }
            }

            if (source == radio2) {

                // show list of all transactions
                text.setOpaque(false);
                message = "Transactions List for \n"+ Main.name 
                        + "\n\nID \t Type \t\t\t Amount\n\n";

                for (int i = 0; i < checkingAccountObj.getTransCount(); i++) {
                    message += checkingAccountObj.getTrans(i).toString() + "\n";
                }
                text.setText(message);

                JOptionPane.showMessageDialog(null, text);
            }
            if (source == radio3) {
                // show list of all all cheks 
                text.setOpaque(false);
                message = "List All Checks for "+ Main.name + "\n\n"
                        + "ID \t Type \t Check Number \t Amount\n\n";
                for (int i = 0; i < checkingAccountObj.getTransCount(); i++) {
                    if (checkingAccountObj.getTrans(i).getTransId().equals("Check")) {
                        message += checkingAccountObj.getTrans(i).toStringCheck() + "\n";
                    }
                }
                text.setText(message);
                JOptionPane.showMessageDialog(null, text);
            }
            if (source == radio4) {
                // show list of all deposits
                text.setOpaque(false);
                message = "List All Deposits for " + Main.name + "\n\n"
                        + "ID \t Type \t\t      Checks \t   Cash \t     Amount\n\n";
                for (int i = 0; i < checkingAccountObj.getTransCount(); i++) {
                    if (checkingAccountObj.getTrans(i).getTransId().equals("Deposit")) {
                        message += checkingAccountObj.getTrans(i).toStringDeposit() + "\n";
                    }
                }
                text.setText(message);
                JOptionPane.showMessageDialog(null, text);
            }
            
            if (source == radio5){
                // read from file goes here:
                
                     readElements();
            }
            
            if (source == radio6){
                writeElements();
                
   
            }
        }
    }

    public int getTransCode() {
        String tranC = JOptionPane.showInputDialog("Enter The Transaction Code "
                + "\n 1 for Check \n 2 for deposit \n 0 for exit");
        transactionCode = Integer.parseInt(tranC);

        return transactionCode;
    }
    
    
    /*
    
   THIS IS THE NEW CODE WITH I HAVE ADDED FOR ASSIGMENT 4 BESIDES, VARIBLE IN THE HEAD
    */
    
    
     public static void chooseFile(int ioOption) 
   {  
      int status, confirm;       
                
      String  message = "Would you like to use the current default file: \n" + filename;
      confirm = JOptionPane.showConfirmDialog (null, message);
      if (confirm == JOptionPane.YES_OPTION)
          return;
      JFileChooser chooser = new JFileChooser();
      if (ioOption == 1)
          status = chooser.showOpenDialog (null);
      else
          status = chooser.showSaveDialog (null);
      if (status == JFileChooser.APPROVE_OPTION)
      {
          File file = chooser.getSelectedFile();
          filename = file.getPath();
      }
   }
    
     
     
     public static void readElements() 
   {  
         chooseFile(1);	
	try
            
		{
			FileInputStream fis = new FileInputStream(filename); 
			ObjectInputStream in = new ObjectInputStream(fis);
                        
                        
                        checkingAccountObj = (CheckingAccount)in.readObject();
                        
			in.close();
		}	
		catch(ClassNotFoundException | IOException e)	
                 { 
                     System.out.println(e);
                 }
   }
     
     
   public void writeElements() 
   {  
        chooseFile(2);
        
      	try
		{
			FileOutputStream fos = new FileOutputStream(filename);   
			ObjectOutputStream out = new  ObjectOutputStream(fos);
                        
                        out.writeObject(checkingAccountObj);
                        
                        out.close();
		}	
                    catch(IOException e)	
                { 
                     System.out.println(e);
                }
   }

}
