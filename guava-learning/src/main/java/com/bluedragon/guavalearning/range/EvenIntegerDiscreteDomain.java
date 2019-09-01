package com.bluedragon.guavalearning.range;

import com.google.common.collect.DiscreteDomain;

public class EvenIntegerDiscreteDomain extends DiscreteDomain<Integer> {

    private Integer min;
    private Integer max;

    public EvenIntegerDiscreteDomain(int min, int max) {
        min = min % 2 == 0 ? min : min - 1;
        max = max % 2 == 0 ? max : max - 1;
        this.min=min;
        this.max=max;
    }


    @Override
    public Integer next(Integer referValue) {
        int referEvenValue = referValue % 2 == 0 ? referValue : referValue - 1;
        int next = referEvenValue + 2;
        next = next > this.max ? this.max : next;
        return next;
    }

    @Override
    public Integer previous(Integer referValue) {
        int referEvenValue = referValue % 2 == 0 ? referValue : referValue - 1;
        int previous = referEvenValue - 2;
        previous = previous < this.min ? this.min : previous;
        return previous;
    }

    @Override
    public long distance(Integer first, Integer second) {
        first = first % 2 == 0 ? first : first - 1;
        second = second % 2 == 0 ? second : second - 1;
        return (second - first) / 2;
    }

    @Override
    public Integer minValue() {
        return this.min;
    }

    @Override
    public Integer maxValue() {
        return this.max;
    }
}
