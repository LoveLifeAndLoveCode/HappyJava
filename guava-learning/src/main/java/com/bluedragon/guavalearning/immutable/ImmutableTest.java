package com.bluedragon.guavalearning.immutable;

import com.google.common.collect.*;

/**
 * @author CodeRush
 * @date 2019/8/3 22:32
 */
public class ImmutableTest {

    /*
    当对象被不可信的库调用时，不可变形式是【安全】的；
    不可变对象被多个线程调用时，【不存在竞态条件问题】
    不可变集合不需要考虑变化，因此【可以节省时间和空间】。所有不可变的集合都比它们的可变形式有【更好的内存利用率】（分析和测试细节）；
    不可变对象因为有固定不变，【可以作为常量来安全使用】。
    创建对象的不可变拷贝是一项很好的【防御性编程技巧】。Guava为所有JDK标准集合类型和Guava新集合类型都提供了简单易用的不可变版本。
    */

    public static void main(String[] args) {
        //创建 3钟方式
        //1、of方法
        ImmutableList<String> immutableList = ImmutableList.of("1", "3", "2");
        System.out.println(immutableList);
        //2、copyOf方法
        ImmutableList<Object> immutableList1 = ImmutableList.copyOf(Lists.newArrayList(1, "abc", 3d));
        System.out.println(immutableList1);
        //3、builder构建
        ImmutableList<Integer> immutableList2 = ImmutableList.<Integer>builder().add(3).add(5).add(7).build();
        System.out.println(immutableList2);

        ImmutableSet<String> immutableSet = ImmutableSet.of("beibei", "jingjing", "huanhuan");
        System.out.println(immutableSet);
        //不可变集合都可以使用asList方法返回一个ImmutableList视图
        ImmutableList<String> immutableSetToList = immutableSet.asList();
        System.out.println(immutableSetToList);

        //会自动排序
        ImmutableSortedSet<String> immutableSortedSet = ImmutableSortedSet.of("1", "32", "3");
        System.out.println(immutableSortedSet);

        ImmutableMap<String, Integer> immutableMap = ImmutableMap.of("1", 11, "2", 22);
        System.out.println(immutableMap);
        System.out.println(immutableMap.asMultimap());
    }

}
