package lotto;

import java.util.List;
import java.util.Map;

public class OutputView {
  /**
   * 예외 발생 시 에러 메시지를 출력합니다.
   *
   * @param errorMessage 에러 메시지
   */
  public void printError(String errorMessage) {
    System.out.println(errorMessage);
  }

  /**
   * 구매한 로또 수와 각 로또 번호를 출력합니다.
   *
   * @param lottos 생성된 lotto 객체 리스트
   */
  public void printLottoInfos(List<Lotto> lottos) {
    System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
    for (Lotto lotto : lottos) {
      System.out.println(lotto.getNumbers());
    }
  }

  /**
   * 당첨 통계를 출력합니다.
   *
   * @param stats 각 Rank별 당첨 횟수가 담긴 Map
   * @param profitRate 계산된 수익률
   */
  public void printStatistics(Map<Rank, Integer> stats, double profitRate) {
    System.out.println("\n당첨 통계");
    System.out.println("---");

    List<Rank> ranksToPrint = List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);

    for (Rank rank : ranksToPrint) {
      int count = stats.getOrDefault(rank, 0);
      System.out.printf("%s (%,d원) - %d개%n", rank.getDescription(), rank.getPrizeMoney(), count);
    }
    System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
  }
}
