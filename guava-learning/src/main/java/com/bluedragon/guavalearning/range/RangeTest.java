package com.bluedragon.guavalearning.range;

import com.google.common.collect.BoundType;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

/**
 * @author CodeRush
 * @date 2019/8/14 23:49
 */
public class RangeTest {

    public static void main(String[] args) {
        //创建一个空的开区间集合
        Range<Integer> open = Range.open(0, 1);
        Range<Integer> emptyRange = Range.closedOpen(5, 5);
        boolean empty = open.isEmpty();
        System.out.println(emptyRange.isEmpty());
        //是否为空集合
        System.out.println(empty);
        //是否包含
        boolean contains = open.contains(0);
        System.out.println(contains);
        //是否有下界
        boolean hasLowerBound = open.hasLowerBound();
        System.out.println(hasLowerBound);
        //是否有上界
        boolean hasUpperBound = open.hasUpperBound();
        System.out.println(hasUpperBound);

        //下界类型：CLOSED/OPEN 闭/开
        System.out.println(open.lowerBoundType());
        //上界类型
        System.out.println(open.upperBoundType());
        //下界值
        System.out.println(open.lowerEndpoint());
        //上界值
        System.out.println(open.upperEndpoint());

        Range<Integer> openTemp = Range.open(0, 3);
        System.out.println(openTemp.contains(1));
        try {
            Range<Integer> open2 = Range.open(0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("创建参数错误");
        }
        //闭区间
        Range<Integer> closed = Range.closed(1, 5);
        System.out.println(closed.lowerBoundType());
        System.out.println(closed.upperBoundType());
        System.out.println(closed.lowerEndpoint());
        System.out.println(closed.upperEndpoint());

        //左开右闭
        Range<Integer> openClosed = Range.openClosed(3, 10);
        System.out.println(openClosed);
        //左闭右开
        Range<Integer> closedOpen = Range.closedOpen(-5, 2);
        System.out.println(closedOpen);

        //最小（包含）-正无穷
        Range<Integer> atLeast = Range.atLeast(-1);
        System.out.println(atLeast);
        //负无穷-最大（包含）
        Range<Integer> atMost = Range.atMost(100);
        System.out.println(atMost);
        //负无穷-此值（小于说明不包含）
        Range<Integer> lessThan = Range.lessThan(99);
        System.out.println(lessThan);
        //此值（大于说明不包含）-正无穷
        Range<Integer> greaterThan = Range.greaterThan(0);
        System.out.println(greaterThan);

        //负无穷-正无穷
        Range<Comparable<?>> all = Range.all();
        System.out.println(all);
        //负无穷-上限（开闭自己指定）
        Range<Integer> upTo = Range.upTo(10, BoundType.CLOSED);
        System.out.println(upTo);
        //下限（开闭自己指定）-正无穷
        Range<Integer> downTo = Range.downTo(1, BoundType.OPEN);
        System.out.println(downTo);

        //只包含一个元素的区间，左右都是闭合的，单值区间
        Range<Integer> singleton = Range.singleton(1);
        System.out.println(singleton);
        //上下界及开闭都自己指定
        Range<Double> range = Range.range(1.0, BoundType.CLOSED, 123.22, BoundType.OPEN);
        System.out.println(range);

        /**交、并**/
        Range<Integer> open1 = Range.open(-1, 1);
        Range<Integer> closed1 = Range.closed(10, 15);
        Range<Integer> openClosed1 = Range.openClosed(-1, 11);
        Range<Integer> closedOpen1 = Range.closedOpen(0, 9);
        //区间是否包含了某区间
        System.out.println(open1.encloses(closed1));
        System.out.println(openClosed1.encloses(open1));
        System.out.println(closedOpen1.encloses(open1));
        //交集，如果没有交集会抛出非法参数异常
        try {
            Range<Integer> intersection = open1.intersection(closed1);
            System.out.println(intersection);
        } catch (IllegalArgumentException e) {
            System.out.println("没有交集，抛出非法参数异常");
        }
        Range<Integer> intersection1 = closed1.intersection(openClosed1);
        System.out.println(intersection1);

        //返回”同时包括两个区间的最小区间”，如果两个区间相连，那就是它们的并集。
        Range<Integer> span = open1.span(openClosed1);
        System.out.println(span);
        Range<Integer> span1 = closed1.span(openClosed1);
        System.out.println(span1);

        //判断是否有区间同时位于这两个区间之间->是否有交集
        System.out.println(open1.isConnected(closed1));
        System.out.println(open1.isConnected(openClosed1));
        System.out.println(Range.open(1, 2).isConnected(Range.open(2, 3)));


        //离散域
        System.out.println("=================离散域=================");
        DiscreteDomain<Integer> integers = DiscreteDomain.integers();
        //离散域中两个元素的距离
        System.out.println(integers.distance(0, 10));
        //离散域上界
        System.out.println(integers.maxValue());
        //离散域下界
        System.out.println(integers.minValue());
        //某个元素下一个
        System.out.println(integers.next(0));
        //某个元素上一个
        System.out.println(integers.previous(0));
        ContiguousSet<Integer> integerContiguousSet = ContiguousSet.create(Range.open(1, 9), integers);
        Range<Integer> integerRange = integerContiguousSet.range();
        System.out.println(integerRange);//[2‥8]
        System.out.println(integerContiguousSet.headSet(5).range());//[2‥4]
        System.out.println(integerContiguousSet.tailSet(5).range());//[5‥8]

    }
}
