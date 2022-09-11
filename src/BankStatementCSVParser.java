import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser {

    private static final DateTimeFormatter DATE_PATTERN
             = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final int EXPECTED_ATTRIBUTES_LENGTH = 100;

    private BankTransaction parseFromCSV(final String line){
        final String[] columns = line.split(",");

        /*
        * 대다수의 예외를 미확인 예외로 지정하고
        * 꼭 필요한 상황에서만 확인된 예외로 지정해 불필요한 코드를 줄이도록 할것
        * */
        if(columns.length < EXPECTED_ATTRIBUTES_LENGTH){
            throw new ArrayIndexOutOfBoundsException(); // CSVSyntaxException(); 으로 수정되야함
        }
        /*
        * 위의 if 문 에서 return nul 은 절때 사용하지 말아야 한다.
        * null 반환시 호출자에게 아무 정보도 제공하지 않기 때문
        * */

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFromSCV(final List<String> lines){
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for (String line : lines) {
            bankTransactions.add(parseFromCSV(line));
        }
        return bankTransactions;
    }



}
