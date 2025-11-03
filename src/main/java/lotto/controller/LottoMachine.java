package lotto.controller;

import java.util.List;
import lotto.dto.AmountDto;
import lotto.dto.BonusNumberDto;
import lotto.dto.LottoResponseDto;
import lotto.dto.TotalPrizeResponseDto;
import lotto.dto.WinningNumberDto;
import lotto.mapper.AmountMapper;
import lotto.mapper.BonusNumberMapper;
import lotto.mapper.LottoMapper;
import lotto.service.LottoService;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {

    private final LottoService lottoService;

    public LottoMachine(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        try {
            AmountDto amount = getAmount();
            List<LottoResponseDto> lottery = lottoService.getLottery(amount);

            printLotteryByLottoResponse(lottery, amount);

            WinningNumberDto winningNumber = getWinningNumber();
            BonusNumberDto bonusNumber = getBonusNumber();

            TotalPrizeResponseDto totalPrize = lottoService.getTotalPrize(lottery, winningNumber, bonusNumber);

            printResult(totalPrize, amount, lottoService.getReturnRate(amount, totalPrize));
        } finally {
            InputView.closeConsole();
        }
    }

    private void printResult(TotalPrizeResponseDto totalPrize, AmountDto amount, double returnRate) {
        OutputView.printTotalPrize(totalPrize);
        OutputView.printReturnRate(returnRate);
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
