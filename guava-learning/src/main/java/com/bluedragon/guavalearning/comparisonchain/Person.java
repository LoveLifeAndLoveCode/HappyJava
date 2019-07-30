package com.bluedragon.guavalearning.comparisonchain;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * @author CodeRush
 * @date 2019/7/27 23:58
 */
public class Person implements Comparable<Person> {

    private String name;
    private Integer age;
    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(Person that) {
        //ComparisonChain执行一种懒比较： 它执行比较操作直至发现非零的结果， 在那之后的比较输入将被忽略,相对于下面的比较简单明了多了
        return ComparisonChain.start()
                .compare(this.name, that.name, Ordering.natural().nullsFirst())
                .compare(this.age, that.age)
                .compare(this.gender, that.gender)
                .result();
    }

//    class Person implements Comparable {
//        private String lastName;
//        private String firstName;
//        private int zipCode;
//        public int compareTo(Person other) {
//            int cmp = lastName.compareTo(other.lastName);
//            if (cmp != 0) {
//                return cmp;
//            }
//            cmp = firstName.compareTo(other.firstName);
//            if (cmp != 0) {
//                return cmp;
//            }
//            return Integer.compare(zipCode, other.zipCode);
//        }
//    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    /**
     * 性别枚举
     */
    public enum Gender {
        MAN,
        WOMAN
    }
}
