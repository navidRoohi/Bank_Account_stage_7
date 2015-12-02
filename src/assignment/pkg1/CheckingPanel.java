package assignment.pkg1;

/**
 *
 * @author navidroohibroojeni
 */
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckingPanel extends JPanel implements Serializable {

    JFrame menuFrame = new JFrame();
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu accountMenu;
    private JMenu transactionMenu;
    private JMenu helpMenu;

    private JMenuItem readFromFile;
    private JMenuItem writeToFile;
    private JMenuItem exit;

    private JMenuItem addNewAccount;
    private JMenuItem listAccountTransaction;
    private JMenuItem listAllChecks;
    private JMenuItem listAllDeposits;
    private JMenuItem findAnAccount;
    private JMenuItem listAllAccount;

    private JMenuItem addTransaction;

    private JMenuItem help;

    private JTextArea textArea;

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

    JLabel lable;

    double amount = 0;
    String transaction;
    double currentServiceCharge;
    int transactionCode;

    public static CheckingAccount checkingAccountObj;

    String message = "";

    DecimalFormat form;

    public static boolean ifSaved = true;

    ArrayList<CheckingAccount> listAllAccounts;
    

    /*
     Constractor
     */
    public CheckingPanel() {

        listAllAccounts = new ArrayList<>();

        menuFrame = new JFrame("Welcome To The Transaction Account Service");

        // ask user to save before calosing, if is not saved yet
        menuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menuFrame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent ev) {

                exit();
            }
        });

        menuFrame.setPreferredSize(new Dimension(450, 350));

        buildMenuBar();
        menuFrame.pack();
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);

        form = new DecimalFormat("$##,##0.00;($##,##0.00)");

        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        lable = new JLabel("Checking Account Actions");
        lable.setFont(new Font("Helvetica", Font.BOLD, 24));

        add(lable);

    }

    public void exit() {
        int response;

        if (ifSaved) {
            menuFrame.dispose();
        }

        if (!ifSaved) {
            response = JOptionPane.showConfirmDialog(null, "Do you want to Save ? ", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.NO_OPTION) {
                menuFrame.dispose();
            } else {

                CheckingPanel.chooseFile(1);

                menuFrame.dispose();
                

            }
        }
        
        System.exit(0);

    }

    private void buildMenuBar() {
        textArea = new JTextArea();
        textArea.setBackground(Color.ORANGE);
        textArea.setEditable(false);

        menuBar = new JMenuBar();

        buildFileMenu();
        buildAccountMenu();
        buildTransactionMenu();
        buildHelpMenu();

        menuBar.add(fileMenu);
        menuBar.add(accountMenu);
        menuBar.add(transactionMenu);
        menuBar.add(helpMenu);

        menuFrame.setJMenuBar(menuBar);
        menuFrame.add(textArea);
    }

    public void buildHelpMenu() {
        help = new JMenuItem("Project Instructions");
        help.addActionListener(new OpenUrlAction());
        helpMenu = new JMenu("Help");
        helpMenu.add(help);
    }

    //  browser listener 
    class OpenUrlAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                URI uri = new URI("http://www.elcamino.edu/faculty/gscott/JavaAsnG.htm"); 
                open(uri);
            } catch (URISyntaxException io) {
                System.out.println(io);
            }
        }
    }
     //open the browser 
    private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {  }
        } else { }
    }

    public void buildTransactionMenu() {
        addTransaction = new JMenuItem("Add Transaction");

        addTransaction.addActionListener(new addTransaction());

        transactionMenu = new JMenu("Transaction");

        transactionMenu.add(addTransaction);

    }

    private void buildAccountMenu() {
        addNewAccount = new JMenuItem("Add New Account");
        listAccountTransaction = new JMenuItem("List Account Transactions");
        listAllChecks = new JMenuItem("List All Checks");
        listAllDeposits = new JMenuItem("List All Deposits");
        findAnAccount = new JMenuItem("Find An Account");
        listAllAccount = new JMenuItem("List All Accounts");

        addNewAccount.addActionListener(new addNewAccount());
        listAccountTransaction.addActionListener(new listAccountTransaction());
        listAllChecks.addActionListener(new listAllChecks());
        listAllDeposits.addActionListener(new listAllDeposits());
        findAnAccount.addActionListener(new findAnAccount());
        listAllAccount.addActionListener(new listAllAccount());

        accountMenu = new JMenu("Account");

        accountMenu.add(addNewAccount);
        accountMenu.add(listAccountTransaction);
        accountMenu.add(listAllChecks);
        accountMenu.add(listAllDeposits);
        accountMenu.add(findAnAccount);
        accountMenu.add(listAllAccount);

    }

    private void buildFileMenu() {
        readFromFile = new JMenuItem("Read From File");
        writeToFile = new JMenuItem("Write to File");
        exit = new JMenuItem("Exit");

        readFromFile.addActionListener(new readFromFileListener());
        writeToFile.addActionListener(new writeToFileListener());
        exit.addActionListener(new exitListener());

        fileMenu = new JMenu("File");

        fileMenu.add(readFromFile);
        fileMenu.add(writeToFile);
        fileMenu.add(exit);
    }

    public class exitListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            exit();
        }
    }

    public class listAllAccount implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            message = "";

            ArrayList<String> listName = new ArrayList<>();

            for (int i = 0; i < listAllAccounts.size(); i++) {
                listName.add(listAllAccounts.get(i).getName());
            }
            // sort the list
            Collections.sort(listName);

            for (int p = 0; p < listAllAccounts.size(); p++) {
                message += listName.get(p) + "\n";
            }

            textArea.setText(message);

        }
    }

    private class findAnAccount implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            
            ifSaved = false;

            String input = JOptionPane.showInputDialog("Please Enter Account's Name holder: ");

            for (int i = 0; i < listAllAccounts.size(); i++) {
                if (input.equals(listAllAccounts.get(i).getName())) {
                    // show list of all transactions
                     message = "Account: " + input
                    + "\nBalance: $" + listAllAccounts.get(i).getBalance()
                    + "\nTotal Service Charge: $" + listAllAccounts.get(i).getTotalServiceCharge()
                    + "\n\nList of All transactions:  \n\n";
            message += "ID \t Type \t\t\t Amount\n";

                    for (int p = 0; p < listAllAccounts.get(i).getTransCount(); p++) {
                        message += listAllAccounts.get(i).getTrans(p).toString() + "\n";

                    }

                    textArea.setText(message);
                    
                    // update the account to one is searched
                    checkingAccountObj =  listAllAccounts.get(i);
                }
            }
            
            

        }
    }

    private class listAllDeposits implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            // show list of all deposits
            message = "List All Deposits for " + checkingAccountObj.name + "\n\n"
                    + "ID \t Type \t\t      Checks \t   Cash \t     Amount\n\n";
            for (int i = 0; i < checkingAccountObj.getTransCount(); i++) {
                if (checkingAccountObj.getTrans(i).getTransId().equals("Deposit")) {
                    message += checkingAccountObj.getTrans(i).toStringDeposit() + "\n";
                }
            }

            textArea.setText(message);

        }
    }

    private class listAllChecks implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            // show list of all all cheks 
            message = "List All Checks for " + checkingAccountObj.name + "\n\n"
                    + "ID \t Type \t Check Number \t Amount\n\n";
            for (int i = 0; i < checkingAccountObj.getTransCount(); i++) {
                if (checkingAccountObj.getTrans(i).getTransId().equals("Check")) {
                    message += checkingAccountObj.getTrans(i).toStringCheck() + "\n";
                }
            }
            textArea.setText(message);
        }
    }

    private class listAccountTransaction implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            // show list of all transactions
            
            message = "Account: " + checkingAccountObj.getName()
                    + "\nBalance: " + form.format(checkingAccountObj.getBalance())
                    + "\nTotal Service Charge: " + form.format(checkingAccountObj.getTotalServiceCharge())
                    + "\n\nList of All transactions:  \n\n";
            message += "ID \t Type \t\t\t Amount\n";

            for (int i = 0; i < checkingAccountObj.getTransCount(); i++) {
                message += checkingAccountObj.getTrans(i).toString() + "\n";

            }
            

            textArea.setText(message);

        }
    }

    private class addNewAccount implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            ifSaved = false;

            String name = Main.getBalanceName();
            double initBalance = Main.getInitialBalance();

            checkingAccountObj = new CheckingAccount(name, initBalance);

            listAllAccounts.add(checkingAccountObj);

        }
    }

    private class addTransaction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            ifSaved = false;

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

                    JOptionPane.showMessageDialog(null, checkingAccountObj.name + " account.\n"
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
                System.exit(0);
            }
        }
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
            ifSaved = false;

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

                JOptionPane.showMessageDialog(null, checkingAccountObj.name + " account.\n"
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

    public int getTransCode() {
        String tranC = JOptionPane.showInputDialog("Enter The Transaction Code "
                + "\n 1 for Check \n 2 for deposit \n 0 for exit");
        transactionCode = Integer.parseInt(tranC);

        return transactionCode;
    }

    public static void chooseFile(int ioOption) {
        int status, confirm;

        String message = "Would you like to use the current default file: \n" + filename;
        confirm = JOptionPane.showConfirmDialog(null, message);

        JFileChooser chooser = new JFileChooser();

        if (confirm == JOptionPane.YES_OPTION) {
            filename = filename;
        }

        if (confirm == JOptionPane.NO_OPTION) {
            if (ioOption == 1) {
                status = chooser.showOpenDialog(null);
            } else {
                status = chooser.showSaveDialog(null);
            }

            if (status == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                filename = file.getPath();
            }
        }

        if (confirm == JOptionPane.CANCEL_OPTION) {
            return;
        }

    }

    private class readFromFileListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            readElements();
        }
    }

    private class writeToFileListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            writeElements();
        }
    }

    public void readElements() {
        chooseFile(1);
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);

            // there is a problem here  :-O
            
            // checkingAccountObj
            // listAllAccounts.add((CheckingAccount)in.readObject());
           
            CheckingAccount object;
            do{
                object = (CheckingAccount)in.readObject();
                listAllAccounts.add(object);
            } while (object != null);

            
            
            ifSaved = true;
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeElements() {
        chooseFile(2);
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);

               for (int i =0; i< listAllAccounts.size() ; i++){
                     out.writeObject(listAllAccounts.indexOf(i));
               } 
                  
            
            
            
            // out.writeObject(listAllAccounts);
            
            
            
            ifSaved = true;
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
