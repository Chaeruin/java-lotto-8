package lotto.view;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lotto.dto.LottoResponseDto;
import lotto.dto.TotalPrizeResponseDto;
import lotto.enums.Prize;

public class OutputView {

    static DecimalFormat df = new DecimalFormat("###,###");

    public static void printLottery(int count, List<LottoResponseDto> lottery) {
        String countString = String.format("%d개를 구매했습니다.", count);
        System.out.println(countString);
        lottery.forEach(dto -> printNumber(dto.numbers()));
    }

    private static void printNumber(List<Integer> numbers) {
        String result = "[";
        result = result.concat(numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
        result = result.concat("]");
        System.out.println(result);
    }

    public static void printTotalPrize(TotalPrizeResponseDto totalPrize) {
        System.out.println("당첨 통계");
        System.out.println("---");
        Map<Prize, Integer> winnings = totalPrize.winnings();
        printWinningMap(winnings);
    }

    private static void printWinningMap(Map<Prize, Integer> winnings) {
        winnings.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().getBalls()))
                .forEach(entry -> {
                    Prize prize = entry.getKey();
                    int count = entry.getValue();
                    if (!prize.isBonus()) {
                        System.out.printf("%d개 일치 (%s원) - %d개\n",
                                prize.getBalls(), df.format(prize.getPrizeMoney()), count);
                    }
                    if (prize.isBonus()) {
                        System.out.printf("%d개 일치, 보너스 볼 일치 (%s원) - %d개\n",
                                prize.getBalls(), df.format(prize.getPrizeMoney()), count);
                    }});
    }

    public static void printReturnRate(double returnRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.", returnRate);
    }
}
