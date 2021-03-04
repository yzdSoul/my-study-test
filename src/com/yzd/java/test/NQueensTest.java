package com.yzd.java.test;

import com.yzd.java.fucking_algorithm.DFS.NQueens;
import org.junit.Test;

import java.util.List;


/**
 * Created by yzd on 2020/9/16
 */
public class NQueensTest {

    @Test
    public void solveNQueens() {
        List<List<String>> nQueens = new NQueens().solveNQueens(8);
        System.out.println(nQueens.size());
//        System.out.print(nQueens);

    }
}