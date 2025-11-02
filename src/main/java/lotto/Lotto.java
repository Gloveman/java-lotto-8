package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 *  사용자가 구매한 로또를 표현하는 Model class
 * 
 */
public class Lotto {
  private static final int TOTAL_NUMBERS = 6;
  private static final int MIN_NUMBER = 1;
  private static final int MAX_NUMBER = 45;

  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    validate(numbers);
    List<Integer> sortedNumbers = new ArrayList<>(numbers);
    Collections.sort(sortedNumbers);
    this.numbers = sortedNumbers;
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

  /**
   * 구매 정보 출력과 테스트를 위한 로또 번호 getter 메서드
   *
   * @return 구매한 로또 번호
   */
    public List<Integer> getNumbers() {
      return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != TOTAL_NUMBERS) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (Integer number : numbers) {
          if (number < MIN_NUMBER ||  number > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
          }
           if(!uniqueNumbers.add(number)) {
             throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
           }
        }
    }
}