package lotto;

import org.assertj.core.data.Offset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.EnumMap;
import java.util.Map;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoMachineTest {
  private LottoMachine lottoMachine;

  @BeforeEach
  void setup() {
    lottoMachine = new LottoMachine();
  }

  @Test
  @DisplayName("buyLottos: 입력 금액이 천원 단위가 아닌 경우 예외를 던진다")
  void buyLottos_MoneyNotDividedByThousand() {
    assertThatThrownBy(()->lottoMachine.buyLottos(99999,()-> List.of(1,2,3,4,5,6)))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("buyLottos: 유효한 금액 입력 시 올바른 개수의 로또를 생성한다")
  void buyLottos_ShouldBuyCorrectCount() {
    List<Lotto>  lottos = lottoMachine.buyLottos(4000,()-> List.of(1,2,3,4,5,6));
    assertThat(lottos.size()).isEqualTo(4);
  }

  @Test
  @DisplayName("calculateResults: 당첨 통계를 정확하게 계산한다")
  void calculateResults_CalculateResultsCorrectly() {
    WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

    List<Lotto> purchasedLottos = List.of(
        new Lotto(List.of(1, 2, 3, 10, 11, 12)), // 5등 (3개 일치)
        new Lotto(List.of(1, 2, 3, 11, 12, 13)), // 5등 (3개 일치)
        new Lotto(List.of(1, 2, 3, 4, 10, 11)), // 4등 (4개 일치)
        new Lotto(List.of(1, 2, 3, 4, 5, 7)),  // 2등 (5개 + 보너스)
        new Lotto(List.of(10, 11, 12, 13, 14, 15)), // 꽝
        new Lotto(List.of(10, 11, 12, 13, 14, 16))  // 꽝
    );

    Map<Rank, Integer> stats = lottoMachine.calculateResults(purchasedLottos, winningLotto);

    assertThat(stats.get(Rank.FIRST)).isEqualTo(0);
    assertThat(stats.get(Rank.SECOND)).isEqualTo(1);
    assertThat(stats.get(Rank.THIRD)).isEqualTo(0);
    assertThat(stats.get(Rank.FOURTH)).isEqualTo(1);
    assertThat(stats.get(Rank.FIFTH)).isEqualTo(2);
  }

  @DisplayName("calculateProfitRate: 수익률을 올바르게 계산한다")
  @ParameterizedTest
  @CsvSource({
      "8000, 1, 0, 62.5",
      "1000, 0, 1, 5000.0",
      "5000, 1, 1, 1100.0",
      "10000, 0, 0, 0.0"
  })
  void calculateProfitRate_CalculatesCorrectly(int amount, int fifthCount, int fourthCount, double expectedRate) {
    Map<Rank, Integer> stats = new EnumMap<>(Rank.class);
    for (Rank rank : Rank.values()) {
      stats.put(rank, 0);
    }
    stats.put(Rank.FIFTH, fifthCount);
    stats.put(Rank.FOURTH, fourthCount);

    double profitRate = lottoMachine.calculateProfitRate(stats, amount);

    assertThat(profitRate).isEqualTo(expectedRate, Offset.offset(0.01)); //반올림하므로 오차 범위 지정
  }
}
