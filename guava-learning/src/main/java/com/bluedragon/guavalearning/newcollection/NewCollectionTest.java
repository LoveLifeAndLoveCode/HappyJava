package com.bluedragon.guavalearning.newcollection;

import com.google.common.base.MoreObjects;
import com.google.common.collect.*;

import java.util.*;

/**
 * 新的集合类
 *
 * @author CodeRush
 * @date 2019/8/3 23:09
 */
public class NewCollectionTest {

    public static void main(String[] args) {
        System.out.println("================testMultiset================");
        testMultiset();

        System.out.println("================testMultimap================");
        testMultimap();

        System.out.println("================testBimap================");
        testBimap();

        System.out.println("================testTable================");
        testTable();

        System.out.println("================testClassToInstanceMap================");
        testClassToInstanceMap();

        System.out.println("================testRangeSet================");
        testRangeSet();

        System.out.println("================testRangeMap================");
        testRangeMap();
    }

    /**
     * Multiset适合用来创建可以包含多个元素及需要计算元素个数的这种场景的集合
     */
    private static void testMultiset() {
        //创建
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("1");
        multiset.add("6");
        multiset.add("3");
        multiset.add("4");
        multiset.add("5");
        multiset.add("2");
        multiset.add("2");
        multiset.addAll(Lists.newArrayList(null, null));
        System.out.println(multiset);
        //size:返回包含的总个数
        System.out.println("size:" + multiset.size());
        //count：计算集合中出现某元素的个数
        System.out.println(multiset.count("2"));
        //打出有几个2
        multiset.stream().filter(s -> "2".equals(s)).forEach(System.out::println);
        Iterator<String> iterator = multiset.iterator();
        System.out.println("iterator:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //添加指定个数的某元素
        multiset.setCount("9", 3);
        multiset.setCount("2", 3);
        //设置某元素的个数，当某元素的旧个数等于指定的旧个数时设置才生效
        multiset.setCount("1", 2, 3); //不生效，因为原先"1"的个数不是2个
        multiset.setCount("3", 1, 3); //生效，原先"3"的个数是1个，设置后变成3个
        System.out.println(multiset);
        //包含方法 contains（是否包含单个元素）和containsAll（是否包含指定集合的所有元素）
        System.out.println(multiset.contains("abc"));
        System.out.println(multiset.containsAll(Lists.newArrayList("1", "a")));
        System.out.println(multiset.containsAll(Lists.newArrayList("1", "2")));

        //删除 remove（默认移除一个,也可以指定移除多少个），removeAll(移除集合中所有元素全部个数)，还有一种移除方式是使用retainAll(下面有例子)
        multiset.remove("9");
        multiset.remove("2", 2);
        multiset.remove("a");
        multiset.removeAll(Sets.newHashSet("3", "5"));
        System.out.println(multiset);

        //elementSet：返回一个set，去掉重复元素
        Set<String> elementSet = multiset.elementSet();
        System.out.println(elementSet);

        //有类似Map的entrySet方法，可以获得元素及其个数
        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            MoreObjects.ToStringHelper str = MoreObjects.toStringHelper("Multiset.Entry<String>")
                    .add("element", entry.getElement())
                    .add("count", entry.getCount());
            System.out.println(str);
        }

        //retainAll:保留集合中指定的元素,相当于其他的被删除掉了
        boolean b = multiset.retainAll(Lists.newArrayList("1", "3", "9"));
        System.out.println(b);
        System.out.println(multiset);

        //SortedMultiset,也可以用其他方法创建，TreeMultiset是SortedMultiset的子类
        SortedMultiset<Integer> sortedMultiset = TreeMultiset.create();
        sortedMultiset.addAll(Lists.newArrayList(1, 1, 3, 5, 6, 2, 6, 7, 2, 8, 11, 0, 0, 22, 21, 20, 15));
        //取区间子集
        SortedMultiset<Integer> sortedMultiset1 = sortedMultiset.subMultiset(5, BoundType.CLOSED, 21, BoundType.OPEN);
        System.out.println(sortedMultiset1);
        //从某个元素往前子集
        System.out.println(sortedMultiset.headMultiset(5, BoundType.CLOSED));
        //从某个元素往后的子集
        System.out.println(sortedMultiset.tailMultiset(5, BoundType.OPEN));
        //倒序
        SortedMultiset<Integer> descendingMultiset = sortedMultiset.descendingMultiset();
        System.out.println(descendingMultiset);
        System.out.println(descendingMultiset.headMultiset(5, BoundType.CLOSED));
        System.out.println(descendingMultiset.tailMultiset(5, BoundType.OPEN));
    }

    /**
     * Multimap可以很容易地把一个键映射到多个值。换句话说，Multimap是把键映射到任意多个值的一般方式
     * 特别注意：尽管Multimap的实现用到了Map，但Multimap<K, V>不是Map<K, Collection<V>>
     */
    private static void testMultimap() {
        ListMultimap<String, String> listMultimap = ArrayListMultimap.create();
        //添加两个方法put和putAll
        listMultimap.putAll("a", Lists.newArrayList("a1", "a2", "a3"));
        listMultimap.put("b", "b1");
        listMultimap.put("b", "b2");
        listMultimap.putAll("c", Lists.newArrayList("c1", "c1", "c3", "c5", "c5", "c5"));
        System.out.println(listMultimap);

        //获取值
        System.out.println(listMultimap.get("a"));
        System.out.println(listMultimap.get("c"));
        //获取不存在的键对应的值会返回空集合
        System.out.println(listMultimap.get("d"));

        //返回每个映射的个数，应为11而不是3
        System.out.println(listMultimap.size());
        System.out.println(listMultimap.keys().size());
        //获取不重复键的个数
        System.out.println(listMultimap.keySet().size());
        for (Map.Entry<String, String> entry : listMultimap.entries()) {
            String str = MoreObjects.toStringHelper("Map.Entry<String, String>")
                    .add("key", entry.getKey())
                    .add("value", entry.getValue()).toString();
            System.out.println(str);
        }
        //返回键的Multiset
        System.out.println("listMultimap.keys():" + listMultimap.keys());
        //返回键的Set
        System.out.println("listMultimap.keySet():" + listMultimap.keySet());
        //返回所有值的集合
        System.out.println("listMultimap.values():" + listMultimap.values());
        System.out.println(listMultimap.containsKey("a"));
        System.out.println(listMultimap.containsKey("d"));
        System.out.println(listMultimap.containsValue("a1"));
        System.out.println(listMultimap.containsValue("d1"));
        System.out.println(listMultimap.containsEntry("a", "a1"));
        System.out.println(listMultimap.containsEntry("a", "a5"));

        //转换成真正的Map<String, Collection<String>> ，这相当于是一个视图
        Map<String, Collection<String>> collectionMap = listMultimap.asMap();
        System.out.println("collectionMap.keySet():" + collectionMap.keySet());
        System.out.println("collectionMap.values():" + collectionMap.values());
        System.out.println(collectionMap);
        System.out.println(collectionMap.size());
        System.out.println(collectionMap.get("a"));
        //获取不存在的键对应的值会返回null
        System.out.println(collectionMap.get("d"));
        //put操作不能做，但是可以做remove，改变会映射到原来集合
//        collectionMap.put("d", Lists.newArrayList("d5", "d3", "d7"));
        //删除存在的键 返回被删除的对象
        System.out.println(collectionMap.remove("c"));
        //删除不存在的键 返回null
        System.out.println(collectionMap.remove("d"));
        System.out.println(collectionMap);
        System.out.println(listMultimap);

        //替换某个键的值，存在则替换，【不存在则添加】
        listMultimap.replaceValues("a", Lists.newArrayList("aa", "aaa"));
        listMultimap.replaceValues("d", Lists.newArrayList("dd"));
        System.out.println(listMultimap);
        //视图也会跟着变
        System.out.println(collectionMap);
        //移除
        listMultimap.removeAll("a");
        listMultimap.remove("b", "b1");
        listMultimap.put(null, null);
        System.out.println(listMultimap);
    }

    /**
     * 实现键值对的双向映射
     */
    private static void testBimap() {
        BiMap<String, Integer> nameToAge = HashBiMap.create();
        nameToAge.put("a", 10);
        nameToAge.put("b", 20);
        nameToAge.putAll(ImmutableMap.of("c", 30, "d", 40));
        System.out.println(nameToAge);
        try {
            nameToAge.put("d", 10);
        } catch (IllegalArgumentException e) {
            System.out.println("不能把键映射到已经存在的值");
            e.printStackTrace();
        }
        //强制覆盖，这是后有值为10的键值对a-10会被覆盖掉，d-40也会变成d-10
        nameToAge.forcePut("d", 10);
        System.out.println(nameToAge);
        for (Map.Entry<String, Integer> entry : nameToAge.entrySet()) {
            String str = MoreObjects.toStringHelper("Map.Entry<String, Integer>")
                    .add("key", entry.getKey())
                    .add("value", entry.getValue())
                    .toString();
            System.out.println(str);
        }

        //反转
        BiMap<Integer, String> ageToName = nameToAge.inverse();
        System.out.println(ageToName);

    }

    /**
     * 使用两个键做索引，理解为表格，行和列可以确定唯一一个值
     */
    private static void testTable() {
        HashBasedTable<String, Integer, Object> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put("a", 1, "a1");
        hashBasedTable.put("b", 2, "b2");
        hashBasedTable.put("c", 3, 3);
        hashBasedTable.put("d", 4, 4.0);
        hashBasedTable.put("d", 4, 5.0);
        System.out.println(hashBasedTable);
        //根据行和列获取值
        System.out.println(hashBasedTable.get("a", 1));
        System.out.println(hashBasedTable.get("a", 1.0));

        //获取某行
        System.out.println(hashBasedTable.row("b"));
        //获取行的所有键的集合
        System.out.println(hashBasedTable.rowKeySet());
        //获取行map
        Map<String, Map<Integer, Object>> rowMap = hashBasedTable.rowMap();
        System.out.println(rowMap);

        //获取某列
        System.out.println(hashBasedTable.column(3));
        //获取列的所有键的集合
        System.out.println(hashBasedTable.columnKeySet());
        //获取列map
        Map<Integer, Map<String, Object>> columnMap = hashBasedTable.columnMap();
        System.out.println(columnMap);

        Set<Table.Cell<String, Integer, Object>> cells = hashBasedTable.cellSet();
        for (Table.Cell<String, Integer, Object> cell : cells) {
            String str = MoreObjects.toStringHelper("Table.Cell<String, Integer, Object>")
                    .add("rowKey", cell.getRowKey())
                    .add("columnKey", cell.getColumnKey())
                    .add("value", cell.getValue())
                    .toString();
            System.out.println(str);
        }
    }

    /**
     * 我们之所以使用ClassToInstanceMap而不是使用Map<Class, Object>,就是因为ClassToInstanceMap使用了MapConstraint,
     * 它保证了我们放入的Class和Object的类型是对应的, 而不会出现 put(Integer.class, "string")这样的情况.
     */
    private static void testClassToInstanceMap() {
        ClassToInstanceMap classToInstanceMap = MutableClassToInstanceMap.create();
        //必须存放对应类型，否则抛出异常
        classToInstanceMap.putInstance(Integer.class, 1);
        classToInstanceMap.putInstance(Double.class, 2.0);
        classToInstanceMap.putInstance(String.class, "3");
        classToInstanceMap.putInstance(String.class, "123");
        classToInstanceMap.put(Float.class, 5f);
        classToInstanceMap.putInstance(List.class, Lists.newArrayList("1", "2", "3"));
        System.out.println(classToInstanceMap);
        System.out.println(classToInstanceMap.get(Integer.class));
        System.out.println(classToInstanceMap.getInstance(Integer.class));
        System.out.println(classToInstanceMap.getInstance(List.class));


        ClassToInstanceMap<String> classToInstanceMap2 = MutableClassToInstanceMap.create();
//        classToInstanceMap2.putInstance(Integer.class, 1); //编译报错
        //只能放指定类型及其子类
        classToInstanceMap2.putInstance(String.class, "22");
        System.out.println(classToInstanceMap2);
    }

    /**
     * 区间集合
     */
    private static void testRangeSet() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));
        rangeSet.add(Range.closedOpen(15, 20));
        rangeSet.add(Range.open(25, 30));
        rangeSet.add(Range.openClosed(35, 50));
        System.out.println(rangeSet);
        //判断是否包含元素
        System.out.println(rangeSet.contains(15));
        System.out.println(rangeSet.contains(20));
        //返回包含元素的区间，没有则返回null
        System.out.println(rangeSet.rangeContaining(15));
        System.out.println(rangeSet.rangeContaining(20));
        //判断是否被其中的某个区间包含
        System.out.println(rangeSet.encloses(Range.closed(17, 19)));
        System.out.println(rangeSet.encloses(Range.closed(17, 20)));
        System.out.println(rangeSet.encloses(Range.closedOpen(17, 20)));
        System.out.println(rangeSet.encloses(Range.closed(17, 21)));
        //返回包括RangeSet中所有区间的最小区间。
        System.out.println(rangeSet.span());
        //返回所有区间set
        Set<Range<Integer>> ranges = rangeSet.asRanges();
        ranges.forEach(System.out::println);
        //如果加进去正好补全了中间某个区段，则这两个区间段合并成一个
        rangeSet.add(Range.closed(20, 27));
        System.out.println(rangeSet);
        //同理，移除的区间段如果在某个更大区间中，则会分割这个区间为两个
        rangeSet.remove(Range.open(22, 25));
        System.out.println(rangeSet);
        //取交集：返回被指定区间包含的所有区间
        System.out.println(rangeSet.subRangeSet(Range.closed(0, 23)));
        System.out.println(rangeSet.subRangeSet(Range.closed(0, 20)));
        //没有交集返回的就是空集
        System.out.println(rangeSet.subRangeSet(Range.closed(55, 66)));
        //取补集
        System.out.println(rangeSet.complement());


        //虽然也可以运行，但是正常都是用在数值上的吧
        RangeSet<String> stringRangeSet = TreeRangeSet.create();
        stringRangeSet.add(Range.open("a", "bb"));
        stringRangeSet.add(Range.closedOpen("1ab", "2cc"));
        System.out.println(stringRangeSet.complement());
        System.out.println(stringRangeSet.span());
//        System.out.println(stringRangeSet.subRangeSet(Range.closed("dsf", "1cc")));
    }

    private static void testRangeMap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(1, 10), "1-10");
        rangeMap.put(Range.closed(5, 12), "5-12");
        rangeMap.put(Range.closed(20, 23), "cc");
        rangeMap.put(Range.openClosed(33, 55), "dd");
        rangeMap.put(Range.openClosed(22, 35), "ff");
        System.out.println(rangeMap);
        System.out.println(rangeMap.get(5));
        System.out.println(rangeMap.get(6));
        Map.Entry<Range<Integer>, String> entry = rangeMap.getEntry(5);
        System.out.println(entry.getKey() + ":" + entry.getValue());
        //返回Map视图
        Map<Range<Integer>, String> rangeStringMap = rangeMap.asMapOfRanges();
        rangeStringMap.forEach((integerRange, s) -> {
            System.out.println(integerRange + "-->" + s);
        });
        //取交集
        System.out.println(rangeMap.subRangeMap(Range.open(11, 22)));
    }
}
