package lotto.view;

import java.text.DecimalFormat;
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
        winnings.forEach((key, value) -> {
            if (!key.isBonus()) {
                System.out.printf("%d개 일치 (%s원) - %d개\n", key.getBalls(), df.format(key.getPrizeMoney()), value);
            }
            if (key.isBonus()) {
                System.out.printf("%d개 일치, 보너스 볼 일치 (%s원) - %d개\n",
                        key.getBalls(), df.format(key.getPrizeMoney()), value);
            }
        });
    }

    public static void printReturnRate(double returnRate) {
        System.out.printf("총 수익률은 %f%%입니다.", returnRate);
    }
}
