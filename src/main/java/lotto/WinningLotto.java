package lotto;

import java.util.List;

/**
 *  당첨 번호와 보너스 번호를 캠슐화한 model class
 *
 */
public class WinningLotto {
  private final List<Integer> numbers;
  private final int  bonusNumber;

  public WinningLotto(List<Integer> numbers, int bonusNumber) {
    this.numbers = numbers;
    this.bonusNumber = bonusNumber;
  }

  /**
   * 구매한 로또 번호 리스트를 받아, 자신의 당첨 번호와 비교하여 등수를 반환
   * 일치하는 번호 수와 보너스 번호 포함 여부 계산
   * {@code Rank.of}에 최종 판별 위임
   *
   * @param randomNumbers 사용자가 구매한 로또 번호 6개
   * @return {@code Rank.of}로 계산한 등수 Enum
   */
  public Rank calculateRank(List<Integer> randomNumbers) {
    int matchCount = (int) randomNumbers.stream()
        .filter(this.numbers::contains)
        .count();
    boolean bonusCorrect = randomNumbers.contains(this.bonusNumber);
    return Rank.of(matchCount, bonusCorrect);
  }
}
