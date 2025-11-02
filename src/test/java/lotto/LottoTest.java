package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

  @DisplayName("로또 번호가 1~45 범위 안에 없으면 예외가 발생한다")
  @Test
  void 로또_번호가_1_45_범위_안에_없으면_예외가_발생한다() {
      assertThatThrownBy(() -> new Lotto(List.of(1, 5, 14, 27, 43, 47)))
                .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("로또 번호가 올바르게 입력되면 오름차순 정렬된 상태로 인스턴스가 생성된다")
  @Test
  void 로또_번호가_올바르게_입력되면_오름차순_정렬된_상태로_인스턴스가_생성된다() {
      Lotto validlotto = new Lotto(List.of(6, 5, 4, 2, 3, 1));
      assertThat(validlotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
  }
}
