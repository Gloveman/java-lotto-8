package lotto;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoMachine
{
  static final int LOTTO_PRICE = 1000;

  /**
   * 로또 번호 생성 전략을 나타내는 functional interface
   */
  @FunctionalInterface
  public interface GenerateNumberStrategy {
    List<Integer> generate();
}

  /**
   * 구매한 로또들과 당첨 번호를 비교하여 등수별 해당하는 로또 수 통계 계산
   *
   * @param lottos 구매 로도 리스트
   * @param winningLotto 당첨 번호 객체
   * @return {등수: 해당 개수} 형태의 Map
   */
  public Map<Rank, Integer> calculateResults(List<Lotto> lottos, WinningLotto winningLotto) {
    Map<Rank, Integer> result = new EnumMap<>(Rank.class);
    for (Rank rank : Rank.values()) {
      result.put(rank, 0);
    }
    for (Lotto lotto : lottos) {
      Rank rank = lotto.checkRank(winningLotto);
      result.put(rank, result.get(rank) + 1);
    }
    return result;
  }

  /**
   * 계산된 통계를 바탕으로 수익률 계산
   *
   * @param result 등수별 로또 수 통계
   * @param amount 총 구입 금액
   * @return 수익률(퍼센트)
   */
  public double calculateProfitRate(Map<Rank, Integer> result, int amount) {
    double totalProfit = 0;
    for (Map.Entry<Rank, Integer> entry : result.entrySet()) {
      Rank rank = entry.getKey();
      int count = entry.getValue();
      totalProfit += (double)rank.getPrizeMoney()*count;
    }
    return (totalProfit/amount) * 100.0;
  }

  /**
   * 구입 금액만큼의 로또 발행
   *
   * @param money 구입 금액
   * @param strategy 번호 생성 전략
   * @return 생성된 Lotto 객체 리스트
   */
  public List<Lotto> buyLottos(int money, GenerateNumberStrategy strategy) {
    if (money%LOTTO_PRICE != 0) {
      throw new IllegalArgumentException("[ERROR] 지불하는 돈은 천원 단위어야 합니다.");
    }
    int count = money / LOTTO_PRICE;
    List<Lotto> lottos = new ArrayList<>();
    for(int i = 0; i < count; i++) {
      lottos.add(buySingleLotto(strategy));
    }
    return lottos;
  }

  private Lotto buySingleLotto(GenerateNumberStrategy strategy) {
    List<Integer> numbers = strategy.generate();
    return new Lotto(numbers);
  }
}
