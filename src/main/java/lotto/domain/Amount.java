package lotto.domain;

import lotto.dto.AmountDto;
import lotto.exception.ValidateErrorCode;

public class Amount {

    private final int amount;

    public Amount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getCountByAmount() {
        return amount / 1000;
    }

    public static Amount from(AmountDto amountDto) {
        return new Amount(amountDto.amount());
    }

    private void validate(int amount) {
        validateIsInRange(amount);
        validateCanDivided(amount);
    }

    private void validateIsInRange(int amount) {
        if (amount > 100_000) {
            throw new IllegalArgumentException(ValidateErrorCode.PURCHASE_AMOUNT_IS_OVER_RANGE.getErrorMessage());
        }
    }

    private void validateCanDivided(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException(ValidateErrorCode.PURCHASE_AMOUNT_IS_NOT_DIVIDED.getErrorMessage());
        }
    }
}
