package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.enums.LottoNumber;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(LottoNumber.MIN_LOTTO_NUMBER.getNumber(),
                LottoNumber.MAX_LOTTO_NUMBER.getNumber(), LottoNumber.LOTTO_SIZE.getNumber());
    }
}
