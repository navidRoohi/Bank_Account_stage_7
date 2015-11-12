package assignment.pkg1;

/**
 *
 * @author navidroohibroojeni
 */
public class Check extends Transaction {

    private int checkNumber;

    public Check(int tCount, int tId, double tAmt, int checkNumber) {
        super(tCount, tId, tAmt);
        this.checkNumber = checkNumber;
    }

    public int getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
    }

    @Override
    public String toStringCheck() {
        return String.format("%2d\t%-10s\t%2d\t$%8.2f", getTransNumber(), getTransId(), getCheckNumber(), getTransAmt());
    }

}
