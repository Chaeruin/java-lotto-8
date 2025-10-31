package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;

public class Issue {

    private final LottoNumberGenerator generator;

    public Issue(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public List<Lotto> issueLottery(int count) {
        List<Lotto> lottery = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottery.add(Lotto.create(generator));
        }
        return lottery;
    }
}
