import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class RefactoredBSCSVAnalyzer {

    /*
    *  놀람 최소화 원칙(principle of least surprise) 가 적용된 클래스
    *  - 다른이가 코드를 보더라도 놀라지 않도록 일관성을 유지하는 범위에서 코드를 구현할 것을 강조하는 원칙
    *  - 주관적인 성격이 강한 원칙으로, 애매할 경우 동료나 팀원과 함께 의논이 필요할 수도 있음
    * */
    private static final String RESOURCES = "src/main/resources/";



    /*
    *  main 에서 파싱 로직을 구현하는 부분이 사라짐 -> 다른 클래스과 메소드에 위임 + 독립적으로 구현 => 캡슐화
    * */
    public static void main(String[] args) throws IOException {

        final BankStatementCSVParser bankStatementCVSParser = new BankStatementCSVParser();

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        // parsing 알고리즘 적용
        final List<BankTransaction> bankTransactions = bankStatementCVSParser.parseLinesFromSCV(lines);

        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));
    }

    /*
    *  모든 거래 내역의 총 결제 금액의 합을 구하는 기능
    * */
    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions){
        double total = 0d;
        for (BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }


    /*
    *  모든 거래 내역 중 특정 월의 거래 내역을 구하는 기능
    * */
    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month){
        final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactions) {
            if(bankTransaction.getDate().getMonth() == month){
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
    }
}
