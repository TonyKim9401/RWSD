import java.time.Month;
import java.util.List;

/*
*  월, 카테고리 같은 BankTransaction 의 속성이 메소드 이름의 빌수로 사용되면
*  인터페이스가 도메인 객체의 특정 접근자에 종속되면
*  도메인 객체의 세부 내용이 바뀌면 인터페이스도 바뀌어야 하며
*  결과적으로 구현 코드도 바뀌어야 하는 문제가 발생한다.
* */
public interface BankTransactionProcessor {
    double calculateTotalAmount();
    double calculateTotalInMonth(Month month);
    double calculateTotalInJanuary();
    double calculateAverageAmount();
    double calculateAverageAmountForCategory(String category);
    List<BankTransaction> findTransactions(BankTransactionFilter bankTransactionFilter);

}
