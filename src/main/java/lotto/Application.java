package lotto;

public class Application {
    public static void main(String[] args) {
        InputView inputview = new InputView();
        OutputView outputview = new OutputView();
        LottoController lottoController = new LottoController(inputview, outputview);
        lottoController.run();
    }
}
