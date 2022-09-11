import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BankTransactionAnalyzerSimple {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        /*
        * 갓 클래스 + 코드 중복 문제 발생
        * 하나의 클래스에 모든 기능을 다 넣어 너무 비대해져 추후 유지,보수에 어려움을 격게 되는
        * 피해야할 안티 패턴중 하나.
        * 새로운 기능을 추가 하거나 삭제시 ctrl + c, ctrl + v 로 해결해야 할 가능성이 있어
        * 코드 중복이 발생할 수도 있다.
        * 이를 피하기 위해 SRP 를 준수하도록 해야한다.
        *
        * SRP 의 특징
        * 1.한 클래스는 한 기능만 책임진다.
        * 2. 클래스가 바뀌어야 하는 이유는 오직 하나여야 한다.
        *
        * SRP 적용 후 개별로 분리해야 하는 내용
        * - 입력 읽기
        * - 주어진 형식의 입력 파싱
        * - 결과 처리
        * - 결과 요약 리포트
        * */
        final Path path = Paths.get(RESOURCES + args[0]);

        // 한줄씩 읽어들임 Files.readAllLines();
        final List<String> lines = Files.readAllLines(path);

        double total = 0d;

        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (String line : lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if(date.getMonth() == Month.JANUARY) {
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }


        System.out.println("The total for all transactions in January is = " + total);

    }
}
