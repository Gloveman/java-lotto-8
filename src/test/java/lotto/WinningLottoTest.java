package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
  private WinningLotto winningLotto;

  @BeforeEach
  void setup() {
    winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6),7);
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
