import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer){
        double result = 0;
        for (BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    /*
    *  거래 내역 총 합
    * */
    public double calculateTotalAmount(){
        double total = 0;
        for (BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    /*
    *  월별 거래 내역 총 합
    * */
    public double calculateTotalInMonth(final Month month){
        return summarizeTransactions((acc, bankTransactions) ->
                bankTransactions.getDate().getMonth() == month ? acc + bankTransactions.getAmount() : acc);
    }

    /*
    *  카테고리별 거래 내역 총 합
    * */
    public double calculateTotalForCatetory(final String category){
        double total = 0;
        for (BankTransaction bankTransaction : bankTransactions) {
            if(bankTransaction.getDescription().equals(category)){
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    /*
    *  특정 금액 이상의 은행 거래 내역 찾기
    * */
    public List<BankTransaction> finaTransactionsGreaterThanEqual(final int amount){
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if(bankTransaction.getAmount() >= amount){
                result.add(bankTransaction);
            }
        }
        return result;
    }

    /*
    *  특정 월의 입출금 내역 찾기
    * */
    public List<BankTransaction> findTransactionsInMonth(final Month month){
        final List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactions) {
            if(bankTransaction.getDate().getMonth() == month){
                result.add(bankTransaction);
            }
        }
        return result;
    }

    /*
    *  특정 월이나 금액으로 입출금 내역 검색하기
    *  -> 한계
    *  - 거래 내역의 여러 속성을 조합할수록 코드가 점점 복잡해진다.
    *  - 반복 로직과 비지니스 로직이 결합되어 분리하기가 어려워진다.
    *  - 코드를 반복한다.
    *
    * => Solution. OCP를 적용한다.
    * */
    public List<BankTransaction> finaTransactionsInMonthAndGreater(final Month month, final int amount){
        final List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactions) {
            if(bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount){
                result.add(bankTransaction);
            }
        }
        return result;
    }

    /*
    *  개방/폐쇄 원칙을 적용한 후 유연해진 findTransactions() 메소드
    *  - 기존 코드를 바꾸지 않으므로 기존 코드가 잘못될 가능성이 줄어든다.
    *  - 코드가 중복되지 않으므로 기존 코드의 재사용성이 높아진다.
    *  - 결합도가 낮아지므로 코드 유지보수성이 좋아진다.
    * */
    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter){
        final List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactions) {
            if(bankTransactionFilter.test(bankTransaction)){
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount){
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
}
