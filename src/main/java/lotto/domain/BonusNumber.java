package lotto.domain;

import lotto.dto.BonusNumberDto;
import lotto.enums.LottoNumber;
import lotto.exception.ValidateErrorCode;

public class BonusNumber {

    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public static BonusNumber from(BonusNumberDto bonusNumberDto) {
        return new BonusNumber(bonusNumberDto.bonusNumber());
    }

    private void validate(int bonusNumber) {
        if (bonusNumber < LottoNumber.MIN_LOTTO_NUMBER.getNumber()
                || bonusNumber > LottoNumber.MAX_LOTTO_NUMBER.getNumber()) {
            throw new IllegalArgumentException(ValidateErrorCode.BONUS_NUMBER_IS_NOT_IN_RANGE.getErrorMessage());
        }
    }
}
