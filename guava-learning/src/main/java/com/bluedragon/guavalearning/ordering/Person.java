package com.bluedragon.guavalearning.ordering;

/**
 * @author chq
 * @date 2019/7/27 20:39
 */
public class Person {
    private String name;
    private Integer age;

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

    @Override
    public String toString() {
        return String.valueOf(name.length());
    }

    public void print() {
        System.out.println("Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}');
    }
}
