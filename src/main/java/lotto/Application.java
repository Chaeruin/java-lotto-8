package lotto;

import lotto.controller.LottoMachine;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.service.Issue;
import lotto.service.ReturnRate;
import lotto.service.WinningLottery;

public class Application {
    public static void main(String[] args) {
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();
        Issue issue = new Issue(generator);
        ReturnRate returnRate = new ReturnRate();
        WinningLottery winningLottery = new WinningLottery();

        LottoMachine lottoMachine = new LottoMachine(issue, returnRate, winningLottery);

        lottoMachine.run();
    }
}
