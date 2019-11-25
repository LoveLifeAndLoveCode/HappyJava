package com.bluedragon.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author CodeRush
 * @date 2019/11/24 22:38
 */
public class TestLang {

    @Test
    public void testStringUtils(){
        //【空、空白】
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertTrue(StringUtils.isNotEmpty("1"));
        Assert.assertTrue(StringUtils.isNotEmpty(" "));
        Assert.assertTrue(StringUtils.isBlank(" "));
        Assert.assertTrue(StringUtils.isBlank("  "));
        Assert.assertTrue(StringUtils.isBlank(""));
        Assert.assertTrue(StringUtils.isBlank(null));

        //【默认值】
        Assert.assertTrue("".equals(StringUtils.defaultString(null)));
        Assert.assertTrue("1".equals(StringUtils.defaultString(null,"1")));

        //【包含】
        //包含任何一个
        Assert.assertTrue(StringUtils.contains("abc", 'a'));
        Assert.assertTrue(StringUtils.contains("abc", "a"));
        Assert.assertTrue(StringUtils.containsAny("abc", "a","ab","ac"));
        Assert.assertTrue(!StringUtils.containsAny("abc", "d","a1b","ac"));
        //不包含任何一个
        Assert.assertTrue(StringUtils.containsNone("abc", 'd','e','f'));
        Assert.assertTrue(StringUtils.containsWhitespace("a  bc"));
        Assert.assertTrue(!StringUtils.containsWhitespace("abc"));
        Assert.assertTrue(StringUtils.containsIgnoreCase("abc","A"));

        //【分割】
        Assert.assertArrayEquals(StringUtils.split(",1,2,,", ","),
                new String[]{"1", "2"});
        Assert.assertArrayEquals(StringUtils.split(null, ","),
                null);
        Assert.assertArrayEquals(StringUtils.split("1|2|", "|"),
                new String[]{"1", "2"});
        Assert.assertArrayEquals(StringUtils.splitByCharacterTypeCamelCase("LoveAndPeace"),
                new String[]{"Love", "And","Peace"});
        Assert.assertArrayEquals(StringUtils.splitByCharacterTypeCamelCase("  LoveAndPeace"),
                new String[]{"  ","Love", "And","Peace"});
        Assert.assertArrayEquals(StringUtils.splitByCharacterTypeCamelCase("sLoveAndPeace"),
                new String[]{"s","Love", "And","Peace"});
        //
        Assert.assertArrayEquals(StringUtils.splitByWholeSeparator("#1#2##", "#"),
                new String[]{"1","2",""});
        Assert.assertArrayEquals(StringUtils.splitByWholeSeparator("###1#2##", "#"),
                new String[]{"1","2",""});
        //设置返回数组的最大长度
        Assert.assertArrayEquals(StringUtils.splitByWholeSeparator("#1#2##", "#",2),
                new String[]{"1","2##"});
        //保留所有分割的，不忽略空格
        Assert.assertArrayEquals(StringUtils.splitPreserveAllTokens("#1#2##", "#"),
                new String[]{"","1","2","",""});
    }

}
