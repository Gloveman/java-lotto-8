package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
  private List<Integer> validWinningNumbers;
  private WinningLotto winningLotto;

  @BeforeEach
  void setup() {
    validWinningNumbers = List.of(1, 2, 3, 4, 5, 6);
    winningLotto = new WinningLotto(validWinningNumbers,7);

  }

  @Test
  @DisplayName("당첨 번호가 잘못 입력되면 예외가 전달된다")
  void constructor_InvalidLottoNumbers_ShouldThrowException() {
    // given
    List<Integer> duplicatedNumbers = List.of(1, 2, 3, 4, 5, 5); // 5 중복
    int validBonusNumber = 7;

    // when & then
    // 'new Lotto(numbers)'에서 발생한 예외가 그대로 전달됨
    assertThatThrownBy(() -> new WinningLotto(duplicatedNumbers, validBonusNumber))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
  void constructor_DuplicateBonusNumber_ShouldThrowException() {
    // given
    int duplicateBonusNumber = 6; // 당첨 번호 '6'과 중복

    // when & then
    assertThatThrownBy(() -> new WinningLotto(validWinningNumbers, duplicateBonusNumber))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다")
  void constructor_BonusNumberOutOfRange_ShouldThrowException() {
    // given
    int lowBonusNumber = 0;
    int highBonusNumber = 46;

    // when & then
    assertThatThrownBy(() -> new WinningLotto(validWinningNumbers, lowBonusNumber))
        .isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> new WinningLotto(validWinningNumbers, highBonusNumber))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("calculateRank: 6개 번호가 모두 일치하면 1등")
  void calculateRank_FirstPlace() {
    List<Integer> userNumbers = List.of(1, 2, 3, 4, 5, 6);
    Rank rank = winningLotto.calculateRank(userNumbers);

    assertThat(rank).isEqualTo(Rank.FIRST);
  }

  @Test
  @DisplayName("calculateRank: 5개 번호와 보너스 번호가 일치하면 2등")
  void calculateRank_SecondPlace() {
    List<Integer> userNumbers = List.of(1, 2, 3, 5, 6, 7); //4 불일치, 보너스 번호 7 일치
    Rank rank = winningLotto.calculateRank(userNumbers);

    assertThat(rank).isEqualTo(Rank.SECOND);
  }

  @Test
  @DisplayName("calculateRank: 5개 번호만 일치하면 3등")
  void calculateRank_ThirdPlace() {
    List<Integer> userNumbers = List.of(1, 2, 3, 4, 6, 8); //5 불일치
    Rank rank = winningLotto.calculateRank(userNumbers);

    assertThat(rank).isEqualTo(Rank.THIRD);
  }

  @Test
  @DisplayName("calculateRank: 4개 번호가 일치하면 4등")
  void calculateRank_FourthPlace() {
    List<Integer> userNumbers = List.of(1, 2, 3, 4, 10, 45); //5, 6 불일치
    Rank rank = winningLotto.calculateRank(userNumbers);

    assertThat(rank).isEqualTo(Rank.FOURTH);
  }

  @Test
  @DisplayName("calculateRank: 3개 번호가 일치하면 5등")
  void calculateRank_FifthPlace() {
    List<Integer> userNumbers = List.of(1, 3, 5, 11, 26, 37);
    Rank rank = winningLotto.calculateRank(userNumbers);

    assertThat(rank).isEqualTo(Rank.FIFTH);
  }

  @Test
  @DisplayName("calculateRank: 2개 이하로 일치하면 꽝(LOSE)")
  void calculateRank_Lose() {
    List<Integer> userNumbers = List.of(1, 12, 13, 14, 15, 16);
    Rank rank = winningLotto.calculateRank(userNumbers);

    assertThat(rank).isEqualTo(Rank.LOSE);
  }
}
