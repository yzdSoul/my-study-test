package com.yzd.java.test;

import com.yzd.java.fucking_algorithm.DFS.Permute;
import org.junit.Test;

import java.util.List;


/**
 * Created by yzd on 2020/9/15
 */
public class PermuteTest {

    int[] arr = new int[]{1,2,3};
    @Test
    public void permute(){
        Permute p = new Permute();
        List<List<Integer>> lists = p.permute(arr);
        System.out.println(lists.size());
        System.out.println(lists.toString());
    }
}