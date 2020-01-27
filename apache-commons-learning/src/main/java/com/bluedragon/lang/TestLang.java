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
        //根据大写字母分割
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

        //【缩写】
        //得到maxWidth-3个字符+"...",maxWidth最小为4
        Assert.assertEquals(StringUtils.abbreviate("abcde 12345", 7), "abcd...");
        Assert.assertEquals(StringUtils.abbreviate("abcde 12345", "哈哈", 7), "abcde哈哈");

        //【计数】
        //计算字符或字符串出现次数
        Assert.assertEquals(StringUtils.countMatches("I Love you so much",'u'),2);
        Assert.assertEquals(StringUtils.countMatches("I Love you so much",' '),4);
        Assert.assertEquals(StringUtils.countMatches("be who you want to beb","be"),2);

        //【返回默认值】
        Assert.assertEquals(StringUtils.defaultString("a"),"a");
        Assert.assertEquals(StringUtils.defaultString(null),"");
        Assert.assertEquals(StringUtils.defaultString(null,"b"),"b");
        Assert.assertEquals(StringUtils.defaultIfBlank(" ","b"),"b");
        Assert.assertEquals(StringUtils.defaultIfEmpty("","b"),"b");

        //比较【可以避免空指针异常】
        Assert.assertEquals(StringUtils.compare("a","b"),-1);
        //默认null比较小
        Assert.assertEquals(StringUtils.compare("a",null),1);
        Assert.assertEquals(StringUtils.compare("a",null,false),-1);
        try {
            //使用String原生的比较方法，没有处理空指针异常
            int i = "a".compareTo(null);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof  NullPointerException);
            System.out.println("空指针异常");
        }

        //【截取】
        Assert.assertEquals(StringUtils.substring("abcde", 1), "bcde");
        Assert.assertEquals(StringUtils.substring("abcde", 6), "");
        Assert.assertEquals(StringUtils.substring(null, 1), null);
        Assert.assertEquals(StringUtils.substringBefore("ab;cde", ";"), "ab");
        Assert.assertEquals(StringUtils.substringAfter("ab;cde", ";"), "cde");
        Assert.assertEquals(StringUtils.substringBetween("ab;|kkk|jjj|cde", "|"), "kkk");
        Assert.assertEquals(StringUtils.substringBetween("ab;[kkk]jjj]cde", "[","]"), "kkk");
        Assert.assertEquals(StringUtils.substringBetween("ab;[kkk[jjj]cde", "[","]"), "kkk[jjj");
        Assert.assertArrayEquals(StringUtils.substringsBetween("abc|de|fg||","|","|"),new String[]{"de",""});
        Assert.assertArrayEquals(StringUtils.substringsBetween("abc|de|fg|||","|","|"),new String[]{"de",""});
        Assert.assertArrayEquals(StringUtils.substringsBetween("abc|de]fg|]","|","]"),new String[]{"de",""});

        //【截断】
        Assert.assertEquals(StringUtils.truncate("2020-01-01 12:00:30", 10), "2020-01-01");
        Assert.assertEquals(StringUtils.truncate("2020-01-01 12:00:30", 2,10), "20-01-01 1");

        //【包裹和解包裹】
        Assert.assertEquals(StringUtils.wrap("111", "kk"), "kk111kk");
        Assert.assertEquals(StringUtils.wrap(null, "kk"), null);
        Assert.assertEquals(StringUtils.wrap("", "kk"), "");
        Assert.assertEquals(StringUtils.unwrap("abckk123kkdef", "kk"), "abckk123kkdef");
        //必须前后位包裹字符串
        Assert.assertEquals(StringUtils.unwrap("kk123kk", "kk"), "123");

        //【左边填充】
        Assert.assertEquals(StringUtils.leftPad("1234567", 9), "  1234567");
        Assert.assertEquals(StringUtils.leftPad("1234567", 9,"kk"), "kk1234567");
        Assert.assertEquals(StringUtils.leftPad("1234567", 9,"kkk"), "kk1234567");
        Assert.assertEquals(StringUtils.leftPad("1234567", 11,"abc"), "abca1234567");

        //【移除】
        Assert.assertEquals(StringUtils.remove("abc de f", ' '), "abcdef");
        Assert.assertEquals(StringUtils.removeStart("abc||de|f", "|"), "abc||de|f");
        Assert.assertEquals(StringUtils.removeStart("||abc||de|f||", "|"), "|abc||de|f||");
        Assert.assertEquals(StringUtils.removeEnd("||abc||de|f||", "|"), "||abc||de|f|");

        //【重复】
        Assert.assertEquals(StringUtils.repeat("abc", 3), "abcabcabc");
        Assert.assertEquals(StringUtils.repeat("abc", "|",3), "abc|abc|abc");

        System.out.println();
    }

}
