package lotto.controller;

import java.util.List;
import lotto.dto.AmountDto;
import lotto.dto.BonusNumberDto;
import lotto.dto.LottoResponseDto;
import lotto.dto.TotalPrizeResponseDto;
import lotto.domain.Issue;
import lotto.domain.ReturnRate;
import lotto.domain.WinningLottery;
import lotto.dto.WinningNumberDto;
import lotto.mapper.AmountMapper;
import lotto.mapper.BonusNumberMapper;
import lotto.mapper.LottoMapper;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {

    private final Issue issue;
    private final ReturnRate returnRate;
    private final WinningLottery winningLottery;

    public LottoMachine(Issue issue, ReturnRate returnRate, WinningLottery winningLottery) {
        this.issue = issue;
        this.returnRate = returnRate;
        this.winningLottery = winningLottery;
    }

    public void run() {
        try {
            AmountDto amount = getAmount();
            List<LottoResponseDto> lottery = LottoMapper.toDto(issue.issueLottery(AmountMapper.toEntity(amount)));

            printLotteryByLottoResponse(lottery, amount);

            WinningNumberDto winningNumber = getWinningNumber();
            BonusNumberDto bonusNumber = getBonusNumber();

            TotalPrizeResponseDto totalPrize = TotalPrizeResponseDto.of(winningLottery.getTotalWinnings(
                            LottoMapper.toEntityList(lottery), LottoMapper.toEntity(winningNumber),
                            BonusNumberMapper.toEntity(bonusNumber)));

            printResult(totalPrize, amount);
        } finally {
            InputView.closeConsole();
        }
    }

    private void printResult(TotalPrizeResponseDto totalPrize, AmountDto amount) {
        OutputView.printTotalPrize(totalPrize);
        OutputView.printReturnRate(returnRate.getReturnRate(
                AmountMapper.toEntity(amount), totalPrize.totalPrizeMoney()));
    }

    private void printLotteryByLottoResponse(List<LottoResponseDto> lottoResponseDto, AmountDto amount) {
        OutputView.printLottery(AmountMapper.toEntity(amount).getCountByAmount(), lottoResponseDto);
    }

    private AmountDto getAmount() {
        while (true) {
            try {
                AmountDto amountDto = InputParser.parseAmount(InputView.getAmount());
                AmountMapper.toEntity(amountDto);
                return amountDto;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningNumberDto getWinningNumber() {
        while (true) {
            try {
                WinningNumberDto winningNumberDto = InputParser.parseWinningNumbers(InputView.getWinningNumbers());
                LottoMapper.toEntity(winningNumberDto);
                return winningNumberDto;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumberDto getBonusNumber() {
        while (true) {
            try {
                BonusNumberDto bonusNumberDto = InputParser.parseBonusNumber(InputView.getBonusNumber());
                BonusNumberMapper.toEntity(bonusNumberDto);
                return bonusNumberDto;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
