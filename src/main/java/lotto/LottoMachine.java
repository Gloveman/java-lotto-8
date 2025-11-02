package lotto;

import java.util.ArrayList;
import java.util.List;

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
