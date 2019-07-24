package com.bluedragon.guavalearning.optional;

import com.google.common.base.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        //Optional.of(T) ) 创建指定引用的Optional实例， 若引用为null则快速失败 optional:可选的
        Optional<Integer> possible = Optional.of(5);
        //present：存在
        System.out.println(possible.isPresent());  //true
        //返回Optional所包含的引用， 若引用缺失， 则抛出
        //java.lang.IllegalStateException
        System.out.println(possible.get()); //5
        //含有默认值的返回,如果为null返回or中的值，否则直接返回引用值
        System.out.println(possible.or(1)); //5
        //Set<T> asSet()) 返回Optional所包含引用的单例不可变集， 如果引用存在， 返回一个只有单一元素的集合，
        // 如果引用缺失， 返回一个空集合。
        System.out.println(possible.asSet()); //[5]

        try {
            Optional<Object> nullOptionalOf = Optional.of(null); //of接收到null会快速失败
        } catch (Exception e) {
            System.out.println("引用为null则快速失败,Optional.of快速失败率，不会创建成功");
            e.printStackTrace();
        }

        //Optional.absent()) 创建引用缺失的Optional实例 absent:不存在
        Optional<Object> nullOptional = Optional.absent();
        System.out.println(nullOptional.isPresent()); //false
        System.out.println(nullOptional.or("值为null")); //值为null
        System.out.println(nullOptional.orNull());//null
        System.out.println(nullOptional.asSet()); //[]

        //Optional.fromNullable(T)) 创建指定引用的Optional实例， 若引用为null则表示缺失
        Optional<Object> nullableOptional = Optional.fromNullable(null); //从允许null的对象生成
        try {
            System.out.println(nullableOptional.get()); //为null的话get方法就会抛出异常，可以使用orNull或者or(null)
        } catch (Exception e) {
            System.out.println("get到null值抛出异常");
            e.printStackTrace();
        }

    }

}
