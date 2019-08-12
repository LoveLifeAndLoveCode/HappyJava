package com.bluedragon.guavalearning.stringtools;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author CodeRush
 * @date 2019/8/12 22:48
 */
public class StringToolsTest {

    public static void main(String[] args) {

        //region Joiner:连接器
        //创建一个连接器，并指定连接字符串
        Joiner joiner = Joiner.on(";");
        //连接多个对象
        String joinerStr = joiner.join(1, "aa", true);
        System.out.println(joinerStr);
        //连接数组中的元素,【注意：如果有null的话会报空指针一场】
        String joinArrayStr = joiner.join(new Object[]{"a", "b"});
        System.out.println(joinArrayStr);
        //连接集合类
        ArrayList<String> list = Lists.newArrayList("aa", "bb", null);
        try {
            String joinListStr = joiner.join(list);
            System.out.println(joinListStr);
        } catch (NullPointerException e) {
            System.out.println("报空指针异常了，连接器没有指定处理null值");
        }
        /**处理null值**/
        //1、跳过null
        String joinListSkipNullStr = joiner.skipNulls().join(list);
        System.out.println(joinListSkipNullStr);
        //2、使用指定字符串替换null
        String joinListUseForNullStr = joiner.useForNull("[null]").join(list);
        System.out.println(joinListUseForNullStr);

        Appendable appendable = new StringBuffer("result:");
        try {
            //连接器链接指定数组或集合并把链接结果加到Appendable中
            Appendable appendableResult = joiner.appendTo(appendable, Sets.newHashSet("kk", "123", "haha"));
            System.out.println(appendableResult.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = joiner.skipNulls().appendTo(new StringBuilder("sb result->"), 1, 2, 3, 5.5);
        System.out.println(stringBuilder);

        //链接Map键值:用MapJoiner
        Joiner.MapJoiner mapJoiner = Joiner.on("^_^")
                .withKeyValueSeparator("=");
        String mapJoinStr = mapJoiner
                .useForNull("[null]")
                .join(ImmutableMap.of("a", 1, "b", 2));
        System.out.println(mapJoinStr); //a=1^_^b=2
        //endregion

        //region Splitter:拆分器
        Splitter splitter = Splitter.on(",")
                .omitEmptyStrings() //或略空字符串
                .trimResults(); //去掉每个元素两边的空格
        Iterable<String> splitIterable = splitter.split(" aa ,|  bb  |,  cc ,dd ,");
        System.out.println(splitIterable);
        for (String str : splitIterable) {
            System.out.println("|" + str + "|");
        }

        //按固定长度拆分
        Splitter fixedLengthSplitter = Splitter.fixedLength(2);
        Iterable<String> fixedLenSplitIterable = fixedLengthSplitter.split("112233555");
        System.out.println(fixedLenSplitIterable);
        fixedLenSplitIterable.forEach(System.out::println);

        //限制只拆分出几个元素
        Splitter limitSplitter = Splitter.on(";").limit(2);
        Iterable<String> limitSplitIterable = limitSplitter.split("1;2;3;4;5");
        System.out.println(limitSplitIterable);
        limitSplitIterable.forEach(System.out::println);

        //on里面也可以传递正则表达式,等价于Splitter.onPattern()
        //trimResults方法可以传递一个匹配器，去掉匹配器前后匹配到的内容
        //endregion

    }

}
