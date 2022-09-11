import java.time.LocalDate;

/*
*  같은 테이터나 도메인 객체를 처리하는 메소드를 그룹화 하기 위해 DAO 를 사용할 수도 있다.
*  - 필요한 일부 기능을 포함하는 클래스 전체를 dependency 로 추가한다는 약점이 있다.
* */
public class BankTransactionDAO {

    public BankTransaction create(final LocalDate date, final double amount, final String description){
        throw new UnsupportedOperationException();
    }

    public BankTransaction read(final long id){
        throw new UnsupportedOperationException();
    }

    public BankTransaction update(final long id){
        throw new UnsupportedOperationException();
    }

    public BankTransaction delete(final long id){
        throw new UnsupportedOperationException();
    }

}
