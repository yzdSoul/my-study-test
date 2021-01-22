package com.yzd.java.test;

import com.yzd.java.fucking_algorithm.CoinChange;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yzd on 2020/9/9
 */
public class CoinChangeTest {

    @Test
    public void coinChDp() {
        CoinChange coin = new CoinChange();
        int[] coins = new int[]{3, 7, 1};
        int amount = 58;
        int i = coin.coinChange2(coins, amount);
        System.out.println(i);
//        System.out.println(Arrays.toString(coin.memo));
    }
}