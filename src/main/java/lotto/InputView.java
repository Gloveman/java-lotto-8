package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

  /**
   * 사용자에게 구매 금액 입력을 요청하고 입력받은 문자열을 반환
   *
   * @return 구입 금액 문자열
   */
  public String readPurchaseAmount() {
    System.out.println("구매 금액을 입력해 주세요.");
    String input = Console.readLine();
    checkEmpty(input, "구매 금액이 입력되지 않았습니다.");
    return input;
  }

  /**
   * 사용자에게 당첨 번호 입력을 요청하고,
   * 쉼표 기준으로 분리 후 '문자열 리스트'로 반환
   * @return 당첨 번호 문자열 리스트
   */
  public List<String> readWinningNumbers() {
    System.out.println("당첨 번호를 입력해 주세요.(쉼표로 구분)");
    String input = Console.readLine() ;
    checkEmpty(input, "당첨 번호가 입력되지 않았습니다.");
    return Arrays.stream(input.split(","))
        .map(String::trim)
        .collect(Collectors.toList());
  }

  /**
   * 사용자에게 보너스 번호 입력을 요청하고 입력받은 문자열을 반환
   *
   * @return 구입 금액 문자열
   */
  public String readBonusNumber() {
    System.out.println("보너스 번호를 입력해 주세요.");
    String input = Console.readLine() ;
    checkEmpty(input, "보너스 번호가 입력되지 않았습니다.");
    return input;
  }

  private void checkEmpty(String input, String errorMessage) {
    if (input == null || input.trim().isEmpty()) {
      throw new IllegalArgumentException("[ERROR] "+errorMessage);
    }
  }
}
