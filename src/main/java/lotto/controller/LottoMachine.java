package lotto.controller;

import lotto.domain.LottoNumberGenerator;
import lotto.service.Issue;
import lotto.service.ReturnRate;
import lotto.service.WinningLottery;

public class LottoMachine {

    private final LottoNumberGenerator generator;
    private final Issue issue;
    private final ReturnRate returnRate;
    private final WinningLottery winningLottery;

    public LottoMachine(LottoNumberGenerator generator, Issue issue,
                        ReturnRate returnRate, WinningLottery winningLottery) {
        this.generator = generator;
        this.issue = issue;
        this.returnRate = returnRate;
        this.winningLottery = winningLottery;
    }

    public void run() {

    }
}
