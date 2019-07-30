package com.bluedragon.guavalearning.objects;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author CodeRush
 * @date 2019/7/27 20:39
 */
public class ObjectsTest {

    private String name="zhangsan";
    private Integer age=18;

    public static void main(String[] args) {
        //用这个比较方法就不用担心空指针异常了
        System.out.println(Objects.equal(null, null));
        System.out.println(Objects.equal("3", null));
        System.out.println(Objects.equal(null, 3));
        System.out.println(Objects.equal("3", 3));
        System.out.println(Objects.equal("3", "3"));

        System.out.println(Objects.hashCode("3"));
        System.out.println(Objects.hashCode("3","3"));

        ObjectsTest self = new ObjectsTest();
        System.out.println(Objects.toStringHelper(self).add("name", self.name).add("age", self.age).toString());

        System.out.println(MoreObjects.toStringHelper("ObjectsTest1").add("name1", self.name).add("age1", self.age).toString());
    }

}
