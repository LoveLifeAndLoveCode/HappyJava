package com.bluedragon.guavalearning.comparisonchain;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @author CodeRush
 * @date 2019/7/27 23:57
 */
public class ComparisonChainTest {

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setName("zs");
        p1.setAge(18);
        p1.setGender(Person.Gender.WOMAN);
        Person p2 = new Person();
        p2.setName("zs");
        p2.setAge(18);
        p2.setGender(Person.Gender.MAN);
        Person p3 = new Person();
        p3.setAge(18);
        p3.setGender(Person.Gender.MAN);
        Person p4 = new Person();
        p4.setName("zs");
        p4.setAge(20);
        p4.setGender(Person.Gender.MAN);

        ArrayList<Person> people = Lists.newArrayList(p1, p2, p3, p4);
        //使用ComparisonChain实现的compareTo方法排序集合
        people.sort(Person::compareTo);
        people.forEach(System.out::println);
    }
}
