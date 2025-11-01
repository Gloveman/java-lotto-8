package lotto;

import java.util.Arrays;

/**
 * 각 등수에 해당하는 값들을 캡슐화한 model Enum
 *
 */
public enum Rank {
  FIRST(6, 2000000000),
  SECOND(5, 30000000,true),
  THIRD(5, 1500000),
  FOURTH(4, 50000),
  FIFTH(3, 5000),
  LOSE(0,0);

  private final int matchCount;
  private final int prizeMoney;
  private final boolean bonusCorrect;

  Rank(int matchCount,int prizeMoney) {
    this(matchCount,prizeMoney,false);
  }

  Rank(int matchCount,int prizeMoney,boolean bonusCorrect) {
    this.matchCount = matchCount;
    this.prizeMoney = prizeMoney;
    this.bonusCorrect = bonusCorrect;
  }

  /**
   * 일치 개수와 보너스 번호 일치 여부를 받아 Rank 계산
   *
   * @param matchCount 일치 개수
   * @param bonusCorrect 보너스 번호 일치 여부
   * @return 해당 등수 정보를 담은 Rank enum
   */
  public static Rank of(int matchCount,boolean bonusCorrect) {
    if (matchCount < FIFTH.matchCount) {
      return LOSE;
    }
    if (matchCount == SECOND.matchCount) {
      if (bonusCorrect) {
        return SECOND;
      }
      return THIRD;
    }
    return Arrays.stream(values())
        .filter(rank->!rank.bonusCorrect) //위의 케이스 제거
        .filter(rank->rank.matchCount==matchCount)
        .findFirst()
        .get();
  }

  /**
   * 당첨금 액수 반환
   *
   * @return 등수에 맞는 당첨금
   */
  public int getPrizeMoney() {
    return prizeMoney;
  }

}
