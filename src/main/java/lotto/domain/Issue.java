package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Issue {

    private final LottoNumberGenerator generator;

    public Issue(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public List<Lotto> issueLottery(Amount amount) {
        int count = amount.getCountByAmount();
        List<Lotto> lottery = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottery.add(Lotto.create(generator));
        }
        return lottery;
    }
}
