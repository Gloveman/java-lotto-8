package lotto;

import java.util.Arrays;

/**
 * 각 등수에 해당하는 값들을 캡슐화한 model Enum
 *
 */
public enum Rank {
  FIRST(6, 2000000000,"6개 일치"),
  SECOND(5, 30000000,"5개 일치, 보너스 볼 일치",true),
  THIRD(5, 1500000, "5개 일치"),
  FOURTH(4, 50000, "4개 일치"),
  FIFTH(3, 5000, "3개 일치"),
  LOSE(0,0, "꽝");

  private final int matchCount;
  private final int prizeMoney;
  private final boolean bonusCorrect;
  private final String description;

  Rank(int matchCount,int prizeMoney, String description) {
    this(matchCount,prizeMoney, description, false);
  }

  Rank(int matchCount,int prizeMoney,String description, boolean bonusCorrect) {
    this.matchCount = matchCount;
    this.prizeMoney = prizeMoney;
    this.description = description;
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

  /**
   * 각 Rank 조건에 대한 설명을 출력합니다.
   *
   * @return Rank별 설명
   */
  public String getDescription() {
    return description;
  }
}
