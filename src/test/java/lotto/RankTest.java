package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
  @DisplayName("of: 일치 번호 수, 보너스 번호 여부에 따라 올바른 등수 정보 반환")
  @ParameterizedTest
  @CsvSource({
      "6, false, FIRST",
      "5, True, SECOND",
      "5, False, THIRD",
      "4, False, FOURTH",
      "3, False, FIFTH",
      "0, False, LOSE"
  })
void of_ReturnsCorrectRank(int matchCount,boolean bonusCorrect, Rank correctRank){
    Rank result = Rank.of(matchCount,bonusCorrect);
    assertThat(result).isEqualTo(correctRank);
  }

  @DisplayName("getPrizeMoney: 등수에 맞는 올바른 상금을 반환")
  @ParameterizedTest
  @CsvSource({
      "FIRST, 2000000000",
      "SECOND, 30000000",
      "THIRD, 1500000",
      "FOURTH, 50000",
      "FIFTH, 5000",
      "LOSE, 0"
  }
  )
  void getPrizeMoney_ReturnsCorrectAmount(Rank rank, int correctAmount){
    int PrizeMoney = rank.getPrizeMoney();
    assertThat(PrizeMoney).isEqualTo(correctAmount);
  }
}
