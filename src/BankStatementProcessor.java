import java.time.Month;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactionList;

    public BankStatementProcessor(List<BankTransaction> bankTransactionList) {
        this.bankTransactionList = bankTransactionList;
    }

    /*
    *  거래 내역 총 합
    * */
    public double calculateTotalAmount(){
        double total = 0;
        for (BankTransaction bankTransaction : bankTransactionList) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    /*
    *  월별 거래 내역 총 합
    * */
    public double calculateTotalInMonth(final Month month){
        double total = 0;
        for (BankTransaction bankTransaction : bankTransactionList) {
            if(bankTransaction.getDate().getMonth() == month){
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    /*
    *  카테고리별 거래 내역 총 합
    * */
    public double calculateTotalForCatetory(final String category){
        double total = 0;
        for (BankTransaction bankTransaction : bankTransactionList) {
            if(bankTransaction.getDescription().equals(category)){
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }
}
