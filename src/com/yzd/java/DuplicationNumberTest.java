package com.yzd.java;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yzd on 2020/5/20
 */
public class DuplicationNumberTest {

    @Test
    public void duplicate() {
        int array[] = {2,3,4,6,5,0,7,6};
        DuplicationNumber duplicationNumber = new DuplicationNumber();
        int i = duplicationNumber.duplicateInArray(array);
        System.out.println(i);
    }
}