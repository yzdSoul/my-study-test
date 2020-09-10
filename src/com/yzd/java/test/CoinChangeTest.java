package com.yzd.java.test;

import com.yzd.java.fucking_algorithm.CoinChange;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yzd on 2020/9/9
 */
public class CoinChangeTest {

    @Test
    public void coinChDp() {
        CoinChange coin = new CoinChange();
        int[] coins = new int[]{1, 5, 2};
        int amount = 13;
        int i = coin.coinChange(coins, amount);
        System.out.println(i);
    }
}