package lotto;

import java.util.List;
import java.util.stream.Collectors;


public class LottoController {
  private final InputView inputView;
  private final OutputView outputView;

  public LottoController(InputView inputView, OutputView outputView) {
    this.inputView = inputView;
    this.outputView = outputView;
  }

  private int getValidPurchaseAmount() {
    while (true){
      try {
        String input = inputView.readPurchaseAmount();
        int amount = parseAmount(input);
        validateAmountIsPositive(amount);
        return amount;
      } catch (IllegalArgumentException e) {
        outputView.printError(e.getMessage());
      }
    }
  }

  private WinningLotto getValidWinningLotto() {
    while (true){
      try {
        List<String> numberStrings = inputView.readWinningNumbers();
        List<Integer> winningNumbers = parseWinningNumbers(numberStrings);

        String bonusString = inputView.readBonusNumber();
        int bonusNumber = parseBonusNumber(bonusString);
        return new WinningLotto(winningNumbers, bonusNumber);
      } catch (IllegalArgumentException e) {
        outputView.printError(e.getMessage());
      }
    }
  }

  private int parseAmount(String input) {
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException eI) {
        throw new IllegalArgumentException("[ERROR] 구매 금액은 숫자여야 합니다.");
    }
  }

  private List<Integer> parseWinningNumbers(List<String> inputStrings) {
    try {
      return inputStrings.stream()
          .map(Integer::parseInt)
          .collect(Collectors.toList());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
    }
  }

  private int parseBonusNumber(String input) {
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException eI) {
      throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
    }
  }

  private void validateAmountIsPositive(int amount) {
    if (amount < 1) {
      throw new IllegalArgumentException("[ERROR] 구매 금액은 양수여야 합니다.");
    }
  }


}
