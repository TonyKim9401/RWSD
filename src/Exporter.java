// 정보를 내보내는 api

public interface Exporter {

    /*
    *  Exporter 인터페이스의 나쁜 예
    * - void 반환 형식은 아무런 도움이 되지 않고 기능 파악도 힘듬
    *  - 구현시 어떤 작업을 진행 후 화면(콘솔)에 출력 해버릴 가능성이 높음
    *  - assertions 를 통한 결과를 테스트 하기도 매우 어려움
    *     -> 예상값 : 실제 결과 비교가 힘듬
    * void export(SummaryStatistics summaryStatistics);
     * */

     /*
     *  Exporter 인터페이스의 좋은 예
     * */
    String export(SummaryStatistics summaryStatistics);


}
