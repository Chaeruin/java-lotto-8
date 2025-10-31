package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;
    private final int LOTTO_SIZE = 6;

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_SIZE);
    }

    public int getMin() {
        return MIN_LOTTO_NUMBER;
    }

    public int getMax() {
        return MAX_LOTTO_NUMBER;
    }

    public int getSize() {
        return LOTTO_SIZE;
    }
}
