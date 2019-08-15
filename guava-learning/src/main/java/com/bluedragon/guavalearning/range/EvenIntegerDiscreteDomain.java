package com.bluedragon.guavalearning.range;

import com.google.common.collect.DiscreteDomain;

public class EvenIntegerDiscreteDomain extends DiscreteDomain<Integer> {

    private Integer min;
    private Integer max;

    public EvenIntegerDiscreteDomain(int min,int max) {
        this.min=min;
        this.max=max;
    }


    @Override
    public Integer next(Integer referValue) {

        return null;
    }

    @Override
    public Integer previous(Integer referValue) {
        return null;
    }

    @Override
    public long distance(Integer first, Integer second) {
        return 0;
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
