import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {

        /*
        * 메인 어플리케이션에서 인터페이스를 사용해 구현한 코드 사용
        * -> 인터페이스 사용으로 결합도를 낮춤
        * */
        final BankStatementAnalyzerWithInterfaceParser bankStatementAnalyzer
                = new BankStatementAnalyzerWithInterfaceParser();

        final BankStatementParser bankStatementParser = new BankStatementCSVParserByInterface();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser);

    }
}
