package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class LottoController {
  private final InputView inputView;
  private final OutputView outputView;

  public LottoController(InputView inputView, OutputView outputView) {
    this.inputView = inputView;
    this.outputView = outputView;
  }

  /**
   * 전체 로직을 실행하는 메서드
   */
  public void run() {
    LottoMachine lottoMachine = new LottoMachine();
    // 1. 구입 금액 입력
    int amount = getValidPurchaseAmount(lottoMachine);
    // 2. 구매한 로또 정보 출력
    List<Lotto> lottos = lottoMachine.buyLottos(amount, () -> Randoms.pickUniqueNumbersInRange(1, 45, 6));
    outputView.printLottoInfos(lottos);
    // 3. 당첨 번호입력
    List<Integer> winningNumbers = getValidWinningNumbers();
    //4. 모델 클래스 생성
    WinningLotto winningLotto = getValidWinningLotto(winningNumbers);
    // 5. 결과 계산
    Map<Rank, Integer> stats = lottoMachine.calculateResults(lottos, winningLotto);
    double profitRate = lottoMachine.calculateProfitRate(stats, amount);
    // 6. 최종 결과 출력
    outputView.printStatistics(stats, profitRate);
  }

  private int getValidPurchaseAmount(LottoMachine lottoMachine) {
    while (true){
      try {
        String input = inputView.readPurchaseAmount();
        int amount = parseAmount(input);
        lottoMachine.validatePurchaseAmount(amount);
        return amount;
      } catch (IllegalArgumentException e) {
        outputView.printError(e.getMessage());
      }
    }
  }

  private List<Integer> getValidWinningNumbers() {
    while (true){
      try {
        List<String> numberStrings = inputView.readWinningNumbers();
        List<Integer> numbers= parseWinningNumbers(numberStrings);
        Lotto.validate(numbers);
        return numbers;
      } catch (IllegalArgumentException e) {
        outputView.printError(e.getMessage());
      }
    }
  }

  private WinningLotto getValidWinningLotto(List<Integer> winningNumbers) {
    while (true){
      try {
        String bonusString = inputView.readBonusNumber();
        int bonusNumber = parseBonusNumber(bonusString);
        return new WinningLotto(winningNumbers,bonusNumber);
      }  catch (IllegalArgumentException e) {
        outputView.printError(e.getMessage());
      }
    }
  }

  int parseAmount(String input) {
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException eI) {
        throw new IllegalArgumentException("[ERROR] 구매 금액은 숫자여야 합니다.");
    }
  }

  List<Integer> parseWinningNumbers(List<String> inputStrings) {
    try {
      return inputStrings.stream()
          .map(Integer::parseInt)
          .collect(Collectors.toList());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
    }
  }

  int parseBonusNumber(String input) {
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException eI) {
      throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
    }
  }
}
