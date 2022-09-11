import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
*  BankTransactionAnalyzerSimple 에 응집도를 적용시킨 코드
* */
public class BankTransactionAnalyzerByProcessor {
    private static final String RESOURCES = "src/main/resources/";
    private static final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        // 파일을 한줄씩 parsing하여 BankTransaction 형식으로 변환
        final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromSCV(lines);

        // 변환된 형식을 BankStatementProcessor 의 생성자에 초기화 시킴
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        // 출력을 응집한 메소드
        collectSummary(bankStatementProcessor);


        // 함수형 인터페이스로 구현한 필터를 적용시킴
/*
        final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(new BankTransactionIsInFebruaryAndExpensive());
        final BankStatementProcessor bankStatementProcessor2 = new BankStatementProcessor(transactions);
*/

        /*
        *  lambda 표현식을 사용해 매번 인터페이스를 만들지 않아도 동일하게 처리 가능
        * */
        List<BankTransaction> transactions = bankStatementProcessor.findTransactions(bankTransaction -> bankTransaction.getDate().getMonth() == Month.FEBRUARY
                && bankTransaction.getAmount() >= 1000);
        final BankStatementProcessor bankStatementProcessor2 = new BankStatementProcessor(transactions);

        collectSummary(bankStatementProcessor2);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor){
        System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("The total for all transactions in January is " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total for all transactions in February is " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("The total salary received is" + bankStatementProcessor.calculateTotalForCatetory("Salary"));

    }
}
