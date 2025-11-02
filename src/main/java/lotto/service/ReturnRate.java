package lotto.service;

import lotto.domain.Amount;

public class ReturnRate {
    
    public double getReturnRate(Amount amount, int totalPrizeAmount) {
        return Math.round(((double) totalPrizeAmount / amount.getAmount() * 100) * 100) / 100.0;
    }
}
