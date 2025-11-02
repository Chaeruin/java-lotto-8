package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import lotto.enums.Prize;

public class TotalPrize {

    private final Map<Prize, Integer> winnings;

    public TotalPrize() {
        winnings = new EnumMap<>(Prize.class);
        Arrays.stream(Prize.values())
                .forEach(prize -> winnings.put(prize, 0));
    }

    public Map<Prize, Integer> getWinnings() {
        return Map.copyOf(winnings);
    }

    public void addPrize(Prize prize, int count) {
        winnings.put(prize, winnings.getOrDefault(prize, 0) + count);
    }

    public int calculateTotalPrizeAmount() {
        return winnings.entrySet().stream()
                .mapToInt(entry ->
                        entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
