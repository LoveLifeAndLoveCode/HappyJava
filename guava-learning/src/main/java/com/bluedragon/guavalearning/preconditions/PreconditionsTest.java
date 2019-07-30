package com.bluedragon.guavalearning.preconditions;

import com.google.common.base.Preconditions;

/**
 * @author CodeRush
 * @date 2019/7/27 20:39
 */
public class PreconditionsTest {

    public static void main(String[] args) {
        String arg = "1";
        Preconditions.checkArgument(arg.equals("1"));
//        Preconditions.checkArgument(arg.equals("2"));
//        Preconditions.checkArgument(arg.equals("2"),"参数不为2");
        String s = Preconditions.checkNotNull(arg);
        System.out.println(s);
//        Object o = Preconditions.checkNotNull(null);

//        Preconditions.checkState(arg.equals("2"));

        int i = Preconditions.checkPositionIndex(3, 2);
        System.out.println(i);
    }

}
