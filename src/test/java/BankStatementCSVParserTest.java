package test.java;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementCSVParserTest {

    private final BankStatementCSVParser statementCSVParser = new BankStatementCSVParser();

    @Test
    public void oldShouldParseOneCorrectLine() throws Exception{
        fail("Not yet implemented");
    }

    @Test
    public void shouldParseOneCorrectLine() throws Exception{
        /*
        *  Given
        *  테스트의 Context를 설정, 예제에서는 파싱할 행을 설정
        * */
        final String line = "30-01-2017,-50,Tesco";

        /*
        *  When
        *  입력 행을 파싱함
        * */
        final BankTransaction result = statementCSVParser.parseFromCSV(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");

        final double tolerance = 0.0d;

        /*
        *  Then
        *  예상되는 결과를 assetion로 지정하여 날짜, 금색, 설명이 제대로 파싱 되었는지 확인한다.
        * */
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount());
        assertEquals(expected.getDescription(), result.getDescription());

    }

    @Test
    public void customTest() throws Exception{
        /*
         *  Given
         *  테스트의 Context를 설정, 예제에서는 파싱할 행을 설정
         * */
        final List<String> lines = new ArrayList<>();
        final String line = "30-01-2017,-50,Tesco";
        lines.add("03-01-2017,30,Tesco");
        lines.add("12-01-2017,40,Tesco");
        lines.add("16-01-2017,120,Tesco");
        lines.add("18-01-2017,-90,Tesco");
        lines.add("19-01-2017,-20,Tesco");
        lines.add("21-01-2017,50,Tesco");
        lines.add("24-01-2017,40,Tesco");
        lines.add("24-01-2017,-70,Tesco2");
        lines.add("26-01-2017,-10,Tesco2");
        lines.add("27-01-2017,90,Tesco2");
        lines.add("29-01-2017,20,Tesco");
        lines.add("27-02-2017,-30,Tesco");
        lines.add("23-02-2017,70,Tesco");


        /*
         *  When
         *  입력 행을 파싱함
         * */
        final BankTransaction result = statementCSVParser.parseFromCSV(line);
        List<BankTransaction> bankTransactions = statementCSVParser.parseLinesFromSCV(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");

        double tolerance = 0.0d;

        double resultAmount = bankStatementProcessor.calculateTotalAmount();
        double resultJanuary = bankStatementProcessor.calculateTotalInMonth(Month.JANUARY);
        double resultCategory = bankStatementProcessor.calculateTotalForCatetory("Tesco");
        /*
         *  Then
         *  예상되는 결과를 assetion로 지정하여 날짜, 금색, 설명이 제대로 파싱 되었는지 확인한다.
         * */

        assertEquals(240d, resultAmount);
        assertEquals(200d, resultJanuary);
        assertNotEquals(240d, resultCategory);
    }


}