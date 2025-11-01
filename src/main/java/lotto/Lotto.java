package lotto;

import java.util.List;


/**
 *  사용자가 구매한 로또를 표현하는 Model class
 * 
 */
public class Lotto {
  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    validate(numbers);
    this.numbers = numbers;
  }

  /**
   * 당첨 번호 객체에게 구매한 번호를 전달해 등수 계산 요청
   *
   * @param winningLotto 당첨 번호(6개 번호 + 1개 보너스 번호)를 표현하는 객체
   * @return 해당 등수 정보를 담은 Rank enum
   */
    public Rank checkRank(WinningLotto winningLotto) {
      return winningLotto.calculateRank(this.numbers);
    }


    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }
}