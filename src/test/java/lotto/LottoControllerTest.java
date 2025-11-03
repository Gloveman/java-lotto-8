package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoControllerTest {
  private LottoController controller;

  @BeforeEach
  void setup() {
    // 테스트시 View는 사용하지 않음
    controller = new LottoController(null, null);
  }

  @Test
  @DisplayName("parseAmount: 유효한 숫자는 int를 올바르게 반환한다")
  void parseAmount_Valid() {
    assertThat(controller.parseAmount("5000")).isEqualTo(5000);
  }

  @Test
  @DisplayName("parseAmount: 숫자가 아닌 문자열은 예외를 던진다")
  void parseAmount_NotANumber_ShouldThrowException() {
    assertThatThrownBy(() -> controller.parseAmount("a"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("parseAmount: 정수 범위를 초과하는 숫자는 예외를 던진다")
  void parseAmount_OutOfIntRange_ShouldThrowException() {
    String largeNumber = "9876543210987";
    assertThatThrownBy(() -> controller.parseAmount(largeNumber))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("parseWinningNumbers: 유효한 숫자 문자열 리스트를 int 리스트로 변환한다")
  void parseNumbers_Valid() {
    List<String> numberStrings = List.of("5", "4", "28", "33", "7", "6");
    assertThat(controller.parseWinningNumbers(numberStrings))
        .isEqualTo(List.of(5, 4, 28, 33, 7, 6));
  }

  @Test
  @DisplayName("parseWinningNumbers: 숫자가 아닌 문자열이 포함되면 예외를 던진다")
  void parseNumbers_NotANumber_ShouldThrowException() {
    List<String> numberStrings = List.of("1", "b", "3", "4", "5", "6");
    assertThatThrownBy(() -> controller.parseWinningNumbers(numberStrings))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("parseBonusNumber: 유효한 숫자는 int를 올바르게 반환한다")
  void parseBonusNumber_Valid() {
    assertThat(controller.parseBonusNumber("7")).isEqualTo(7);
  }

  @Test
  @DisplayName("parseBonusNumber: 숫자가 아닌 문자열은 예외를 던진다")
  void parseBonusNumber_NotANumber_ShouldThrowException() {
    assertThatThrownBy(() -> controller.parseBonusNumber("b"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("[ERROR] 보너스 번호는 숫자여야 합니다.");
  }
}
