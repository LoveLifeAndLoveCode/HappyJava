package com.bluedragon.beanutils;

import com.bluedragon.testbean.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author CodeRush
 * @date 2019/11/24 21:17
 */
public class TestBeanUtils {

    @Test
    public void testCopy() throws InvocationTargetException, IllegalAccessException {
        Student s1 = new Student() {
            {
                setId(1);
                setName("张三");
            }
        };

        Student s2 = new Student();
        //避免用，可以使用SpringUtils的替代，因为性能较差
        BeanUtils.copyProperties(s2, s1);

        Assert.assertEquals(s2.getId(), s1.getId());
        Assert.assertEquals(s2.getName(), s1.getName());
        System.out.println(s2);

        Student s3 = new Student();
        //注意这边1的类型是字符串，也能转换成功
        BeanUtils.copyProperty(s3, "id", "1");
        BeanUtils.copyProperty(s3, "name", "李四");
        Assert.assertTrue(s3.getId() == 1);
        Assert.assertEquals(s3.getName(), "李四");
        System.out.println(s3);
    }

    @Test
    public void testMapHelp() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        student.setAge(18);
        student.setGender(true);

        //bean转成map
        Map<String, String> studentMap = BeanUtils.describe(student);
        System.out.println(studentMap);
        Assert.assertEquals(Integer.valueOf(studentMap.get("id")), student.getId());
        Assert.assertEquals(studentMap.get("name"), student.getName());
        Assert.assertEquals(Integer.valueOf(studentMap.get("age")), student.getAge());
        Assert.assertEquals(Boolean.valueOf(studentMap.get("gender")), student.isGender());

        //map转成bean
        Student student2 = new Student();
        BeanUtils.populate(student2, studentMap);
        Assert.assertEquals(Integer.valueOf(studentMap.get("id")), student2.getId());
        Assert.assertEquals(studentMap.get("name"), student2.getName());
        Assert.assertEquals(Integer.valueOf(studentMap.get("age")), student2.getAge());
        Assert.assertEquals(Boolean.valueOf(studentMap.get("gender")), student2.isGender());
        System.out.println(student2);
    }


}
