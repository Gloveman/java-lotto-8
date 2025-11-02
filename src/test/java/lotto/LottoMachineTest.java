package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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
}
