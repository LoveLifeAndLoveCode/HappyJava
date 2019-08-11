package com.bluedragon.guavalearning.collectiontool;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.*;

/**
 * @author CodeRush
 * @date 2019/8/7 0:02
 */
public class CollectionToolTest {

    public static void main(String[] args) {
        /*
         * Coolection2
         */
        ArrayList<Integer> integers = Lists.newArrayList(10, 2, 5, 7, 11, 6, 3, 2, 8, 1, 0);
        Collection<List<Integer>> permutations = Collections2.permutations(integers);
        System.out.println(permutations);
        Collection<List<Integer>> orderedPermutations = Collections2.orderedPermutations(integers);
        System.out.println(orderedPermutations);
        //转换 转换成其他类型的集合
        Collection<Double> transform = Collections2.transform(integers, Integer::doubleValue);
        System.out.println(transform);
        //java8后这个作用不大
        Collection<Integer> filter = Collections2.filter(integers, input -> input < 9);
        System.out.println(filter);

        //把一个字符串分割成字符List
        ImmutableList<Character> characters = Lists.charactersOf("hello world");
        System.out.println(characters);
        //反转元素顺序
        List<Character> reverseCharacters = Lists.reverse(characters);
        System.out.println(reverseCharacters);
        List<Integer> integers1 = Lists.asList(1, new Integer[]{5, 4, 3});
        System.out.println(integers1);
        List<String> transform1 = Lists.transform(reverseCharacters, c -> c + c.toString());
        System.out.println(transform1);
        //更加语义化的方法
        ArrayList<Object> objects = Lists.newArrayListWithCapacity(5);
        List<List<String>> partition = Lists.partition(transform1, 3);
        System.out.println(partition); //[[dd, ll, rr], [oo, ww,   ], [oo, ll, ll], [ee, hh]

        HashSet<Integer> set1 = Sets.newHashSet(1, 3, 5, 3, 7, 9, 6, 8);
        HashSet<Integer> set2 = Sets.newHashSet(2, 4, 6, 8, 10);
        Set<List<Integer>> cartesianProduct = Sets.cartesianProduct(set1);
        System.out.println(cartesianProduct);
        //交集
        Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);
        System.out.println(intersection);
        //并集
        Sets.SetView<Integer> union = Sets.union(set1, set2);
        System.out.println(union);
        //差集
        Sets.SetView<Integer> difference = Sets.difference(set1, set2);
        System.out.println(difference);
        HashMap<String, Boolean> map = Maps.newHashMap();
        Set<String> setFromMap = Sets.newSetFromMap(map);
        setFromMap.add("aa");
        setFromMap.add("bb");
        setFromMap.add("cc");
        System.out.println(setFromMap);
        System.out.println(map);

        int[] ints = Ints.toArray(integers);
        System.out.println(Ints.contains(ints, 3));
        String join = Ints.join("-", ints);
        System.out.println(join);
        System.out.println(Ints.max(ints));
        System.out.println(Ints.min(ints));

        //根据Iterable子类及一个函数转换成ImmutableMap
        ImmutableMap<Integer, String> integerStringImmutableMap = Maps.toMap(set1, input -> "value" + input);
        System.out.println(integerStringImmutableMap);
    }
}
