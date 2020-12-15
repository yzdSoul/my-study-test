package com.yzd.java.leetcode.algorithmic_thinking;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yzd on 2020/12/15
 */
public class TestSortTest {
    int nums[] = new int[]{3,2,1,5,6,4};
    TestSort sort = new TestSort();

    @Test
    public void runTime(){
        long start,end;
        start = System.currentTimeMillis();
        int kthlargest = new TestSort().findKthlargest3(nums, 2);
        end = System.currentTimeMillis();
        System.out.println("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
    }

    @Test
    public void findKthlargest1() {
        int kthlargest1 = sort.findKthlargest1(nums, 2);
        System.out.println(kthlargest1);
    }

    @Test
    public void findKthlargest2() {
        long start,end;
        start = System.currentTimeMillis();
        int kthlargest2 = sort.findKthlargest2(nums, 2);
        end = System.currentTimeMillis();
        System.out.println("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
        System.out.println(kthlargest2);
    }

    @Test
    public void findKthlargest3() {
        long start,end;
        start = System.currentTimeMillis();
        int kthlargest3 = sort.findKthlargest3(nums, 2);
        end = System.currentTimeMillis();
        System.out.println("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
        System.out.println(kthlargest3);
    }

}