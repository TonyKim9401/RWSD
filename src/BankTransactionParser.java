
/*
*  Parsing 이라는 논리로 그룹화 된 클래스
*  -> 각 메소드들이 서로 관련이 없고, 하나의 클래스가 하나 이상의 책임을 가지게 되므로 SRP또한 위반하게 됨
* */
public class BankTransactionParser {

    public BankTransaction parseFromCSV(final String line){
        throw new UnsupportedOperationException();
    }

    public BankTransaction parseFromJSON(final String line){
        throw new UnsupportedOperationException();
    }

    public BankTransaction parseFromXML(final String line){
        throw new UnsupportedOperationException();
    }


}
