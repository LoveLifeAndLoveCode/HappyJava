package com.bluedragon.guavalearning.ordering;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author CodeRush
 * @date 2019/7/27 20:39
 */
public class OrderingTest {

    public static void main(String[] args) {

        //region 测试数据初始化
        List<String> sourceStringList = Lists.newArrayList("5", "5", "d", "12", "ab", "9", "a", "A", "11", "D");
        List<Integer> sourceIntegerList = Lists.newArrayList(5, 3, 7, 2, 9, 1);
        Person p1 = new Person();
        p1.setName("abcb");
        p1.setAge(8);
        Person p2 = new Person();
        p2.setName("acb");
        p2.setAge(9);
        Person p3 = new Person();
        p3.setName("ff");
        p3.setAge(7);
        Person p4 = new Person();
        p4.setName("dd");
        p4.setAge(8);
        Person p5 = new Person();
        p5.setName("ca");
        p5.setAge(8);
        ArrayList<Person> personList = Lists.newArrayList(p1, p2, p3, p4, p5);

        System.out.println(sourceStringList);
        System.out.println(sourceIntegerList);
        personList.forEach(Person::print);
        System.out.println("原始List输出完毕");
        //endregion

        //region Part I:排序器创建的几种方法及排序
        //1、直接用new创建
        Ordering ordering = new Ordering<String>() {
            //实现compare方法
            @Override
            public int compare(String o1, String right) {
                return o1.compareTo(right);
            }
        };
        //sortedCopy排序并返回排序后的一个新结果集
        List sortedCopyList = ordering.sortedCopy(sourceStringList);
        System.out.println(sortedCopyList);

        //反序==>reverse方法
        List sortedCopyReverseList = ordering.reverse().sortedCopy(sourceStringList);
        System.out.println(sortedCopyReverseList);

        //2、创建自然排序的排序器，用natural方法
        Ordering<Comparable> natural = Ordering.natural();

        //对String自然排序
        List<String> naturalSortList = natural.sortedCopy(sourceStringList);
        System.out.println(naturalSortList);

        //对Integer自然排序
        List<Integer> sortedCopyIntegerList = natural.sortedCopy(sourceIntegerList);
        System.out.println(sortedCopyIntegerList);

        //添加null元素

        sourceStringList.add(null);
        sourceStringList.add(null);

        try {
            // 这时候直接再natural.sortedCopy(sourceStringList)会抛出空指针异常,
            // 所以如果集合中有null要使用排序器、自己实现比较器处理或者过滤null
            List<String> strWithNullList = natural.sortedCopy(sourceStringList);
            System.out.println(strWithNullList);
        } catch (NullPointerException e) {
            System.out.println("======普通的排序没有对null进行处理======");
        }

        //null排在最前面 nullsFirst
        System.out.println(natural.<String>nullsFirst().sortedCopy(sourceStringList));

        //null排在最后面 nullsLast
        System.out.println(natural.nullsLast().sortedCopy(sourceStringList));

        ///3、usingToString创建，使用对象的toString返回结果进行排序
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        // 打印结果name长度小的排前面（person的toString方法返回 String.valueOf(name.length())）
        usingToStringOrdering.sortedCopy(personList).forEach(Person::print);

        //4、根据一个比较器创建排序器
        Ordering<Object> fromOrdering = Ordering.from(Comparator.comparing(o -> {
            //有null自己处理掉了
            return o == null ? "" : o.toString();
        }));
        System.out.println(fromOrdering.sortedCopy(sourceStringList));

        //比较前先调用onResultOf中的方法 根据age进行自然排序
        List<Person> personSortByOnResultOf = natural.onResultOf(Person::getAge).sortedCopy(personList);
        personSortByOnResultOf.forEach(Person::print);
        //endregion

        //region Part II:Ordering对象的其他方法

        //isOrdered
        List<String> tmpList = ImmutableList.of("c", "a", "a", "b");
        //判断当前集合排序是否符合排序器的排序
        System.out.println(natural.isOrdered(tmpList));
        tmpList = natural.sortedCopy(tmpList);
        System.out.println(natural.isOrdered(tmpList)); //true
        System.out.println(natural.isStrictlyOrdered(tmpList)); //false 因为有相同元素
        System.out.println(natural.reverse().isOrdered(tmpList));
        tmpList.remove(0); //移除一个a
        System.out.println(natural.isStrictlyOrdered(tmpList)); //true

        ArrayList<Integer> integerArrayList = Lists.newArrayList(5, 1, 9, 2, 3, 0, 8, 3);
        //获取排序后的最小元素 min
        //直接传入集合
        System.out.println(natural.min(integerArrayList));
        //传入多个元素
        System.out.println(natural.min(7, 5, 9));

        //获取排序后的最大元素 max
        System.out.println(natural.max(integerArrayList));
        System.out.println(natural.max(7, 11, 9, 3));

        //获取排序后最小的n个元素，n由第二个参数指定 leastOf
        System.out.println(natural.leastOf(integerArrayList, 2));

        //获取排序后最大的n个元素，n由第二个参数指定 greatestOf
        System.out.println(natural.greatestOf(integerArrayList, 3));

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(sourceIntegerList);
        lists.add(integerArrayList);

        //lexicographical方法：基于处理类型T的排序器， 返回该类型的可迭代对象Iterable<T>的排序器。
        Ordering<Iterable<Integer>> lexicographical = natural.<Integer>lexicographical();
        //多个集合间的排序（这里是各个集合每个元素轮流比较，某个元素比较后，如果小的话，此集合就往前排）
        System.out.println(lexicographical.sortedCopy(lists));
        //endregion

        //region Part III：复杂排序及链式调用

        //使用compoound合并两个比较器
        //1、复杂排序，先根据age再根据name排序
        //写法一
        List<Person> people = natural.onResultOf(Person::getAge).compound((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        }).sortedCopy(personList);
        people.forEach(Person::print);

        //写法二 （更简单）
        List<Person> people2 = natural.onResultOf(Person::getAge)
                .compound(natural.onResultOf(Person::getName))
                .sortedCopy(personList);
        people2.forEach(Person::print);

        //2、链式调用
        Person nameNullPerson = new Person();
        nameNullPerson.setAge(1);
        personList.add(nameNullPerson);

        /*
         当阅读链式调用产生的排序器时， 应该从后往前读。 下面的例子中， 排序器首先调用getName方法获取name值，
          并把name为null的元素都放到最前面， 然后把剩下的元素按name进行自然排序。 之所
         以要从后往前读， 是因为每次链式调用都是用后面的方法包装了前面的排序器[增强器模式]。
         注： 用compound方法包装排序器时， 就不应遵循从后往前读的原则。 为了避免理解上的混乱， 请不要把
         compound写在一长串链式调用的中间， 你可以另起一行， 在链中最先或最后调用compound。
         */
        List<Person> people13 = Ordering.natural().nullsFirst().onResultOf(Person::getName).sortedCopy(personList);
        people13.forEach(Person::print);

        //endregion


        //region Part IV：【扩展】java8的集合排序（可以替代guava了）
        /*
         * 如果看Ordering源码，注释写道，在Java8中Ordering已经过时了，
         * 因为大部分方法在Comparator中已经实现了！其他的方法可以在guava的Comparators工具类中找到。
         */
        System.out.println("java8的排序");
        //根据名称排序
        personList.sort(Comparator.comparing(Person::getName, Comparator.nullsFirst(String::compareTo)));
        personList.forEach(Person::print);

        //先根据age再根据name，其中age和name都是null值的排在前面
        personList.sort(Comparator.comparing(Person::getAge, Comparator.nullsFirst(Integer::compareTo))
                .thenComparing(Person::getName, Comparator.nullsFirst(String::compareTo)).reversed());

        personList.forEach(Person::print);

        sourceIntegerList.sort(Comparator.naturalOrder());
        System.out.println(sourceIntegerList);
        //endregion

    }
}
