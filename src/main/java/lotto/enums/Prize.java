package lotto.enums;

public enum Prize {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000);

    private final int balls;
    private final boolean isBonus;
    private final int prizeMoney;

    Prize(int balls, boolean isBonus, int prizeMoney) {
        this.balls = balls;
        this.isBonus = isBonus;
        this.prizeMoney = prizeMoney;
    }

    public int getBalls() {
        return balls;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
