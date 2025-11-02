package lotto.controller;

import java.util.List;
import lotto.domain.Amount;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.TotalPrize;
import lotto.dto.AmountDto;
import lotto.service.Issue;
import lotto.service.ReturnRate;
import lotto.service.WinningLottery;
import lotto.util.InputParser;
import lotto.view.InputView;

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
