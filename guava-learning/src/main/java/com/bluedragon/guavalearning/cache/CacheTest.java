package com.bluedragon.guavalearning.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @author CodeRush
 * @date 2019/8/11 23:43
 */
public class CacheTest {
    /**
     * 网上例子：https://www.cnblogs.com/fnlingnzb-learner/p/11022152.html
     */

    public static void main(String[] args) {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(2).build();
        cache.put("1", 1);
        cache.put("2", 2);
        cache.put("3", 3);
        System.out.println(cache.getIfPresent("1"));
        System.out.println(cache.getIfPresent("2"));
        System.out.println(cache.getIfPresent("3"));

    }
}
